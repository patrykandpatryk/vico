/*
 * Copyright 2025 by Patryk Goworowski and Patrick Michalik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.patrykandpatrick.vico.compose.cartesian

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import com.patrykandpatrick.vico.compose.common.detectZoomGestures
import com.patrykandpatrick.vico.core.cartesian.marker.PointerState
import com.patrykandpatrick.vico.core.common.Point

internal fun Modifier.chartTouchEvent(
  setPointerState: ((PointerState) -> Unit)?,
  isScrollEnabled: Boolean,
  scrollState: VicoScrollState,
  onZoom: ((Float, Offset) -> Unit)?,
): Modifier =
  scrollable(
      state = scrollState.scrollableState,
      orientation = Orientation.Horizontal,
      reverseDirection = true,
      enabled = isScrollEnabled,
    )
    .then(
      if (setPointerState != null) {
        pointerInput(setPointerState) {
          awaitPointerEventScope {
            while (true) {
              val event = awaitPointerEvent()
              val pointerPosition = event.changes.first().position.point
              when (event.type) {
                PointerEventType.Press -> setPointerState(PointerState.Pressed(pointerPosition))
                PointerEventType.Release -> setPointerState(PointerState.Released(pointerPosition))
                PointerEventType.Move ->
                  if (!isScrollEnabled) setPointerState(PointerState.Moved(pointerPosition))
              }
            }
          }
        }
      } else {
        Modifier
      }
    )
    .then(
      if (isScrollEnabled && onZoom != null) {
        pointerInput(setPointerState, onZoom) {
          detectZoomGestures { centroid, zoom ->
            setPointerState?.invoke(PointerState.Zoomed(centroid.point))
            onZoom(zoom, centroid)
          }
        }
      } else {
        Modifier
      }
    )

private val Offset.point: Point
  get() = Point(x, y)
