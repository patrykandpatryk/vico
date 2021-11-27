/*
 * Copyright (c) 2021. Patryk Goworowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.patrykgoworowski.vico.core.dataset.renderer

import pl.patrykgoworowski.vico.core.model.Point

public interface RendererViewState {

    public val markerTouchPoint: Point?
    public val horizontalScroll: Float

    public operator fun component1(): Point? = markerTouchPoint
    public operator fun component2(): Float = horizontalScroll
}
