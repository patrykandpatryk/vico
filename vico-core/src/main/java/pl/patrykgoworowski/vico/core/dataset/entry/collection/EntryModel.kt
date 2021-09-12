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

package pl.patrykgoworowski.vico.core.dataset.entry.collection

import pl.patrykgoworowski.vico.core.entry.DataEntry
import kotlin.math.abs

open class EntryModel(
    open val entryCollections: List<List<DataEntry>>,
    open val minX: Float,
    open val maxX: Float,
    open val minY: Float,
    open val maxY: Float,
    open val composedMaxY: Float,
    open val step: Float,
) {

    fun getEntriesLength(): Int =
        (((abs(maxX) - abs(minX)) / step) + 1).toInt()
}
