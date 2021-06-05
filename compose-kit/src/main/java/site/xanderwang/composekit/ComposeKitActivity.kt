/*
 * Copyright 2021 The Android Open Source Project
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
package site.xanderwang.composekit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.xanderwang.composekit.ui.theme.ComposeKitTheme

class ComposeKitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKitTheme {
                ComposeKitApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun ComposeKitApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Compose Kit")
                },
                elevation = 6.dp
            )
        }
    ) {
        SimpleList(
            list = listOf(
                SimpleItem("Java", 0),
                SimpleItem("Kotlin", 0),
                SimpleItem("C++", 0)
            )
        )
    }
}

data class SimpleItem(var name: String, var count: Int)

@Composable
fun SimpleList(list: List<SimpleItem>) {
    Column {
        for (item in list) {
            var mutableItem by remember {
                mutableStateOf(item)
            }
            SimpleItemView(mutableItem) {
                item.count++
                mutableItem = SimpleItem(item.name, item.count)
//                mutableItem = item
            }
        }
    }
}

@Composable
fun SimpleItemView(item: SimpleItem, onClickItem: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        onClick = onClickItem
    ) {
        Text("I am ${item.name}, my count is: ${item.count}")
    }

}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    ComposeKitTheme {
        ComposeKitApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    ComposeKitTheme(darkTheme = true) {
        ComposeKitApp()
    }
}
