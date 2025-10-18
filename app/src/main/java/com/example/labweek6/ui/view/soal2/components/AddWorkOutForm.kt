// Kotlin
package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labweek6.ui.model.soal2.WorkoutFormState

data class WorkoutIcon(val id: String, val imageVector: ImageVector)

@Composable
fun AddWorkOutForm(
    isActive: Boolean,
    state: WorkoutFormState,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onTitleChange: (String) -> Unit,
    onTypeChange: (String) -> Unit,
    onCaloriesChange: (String) -> Unit,
    onIconSelected: (WorkoutIcon) -> Unit
) {
    if (isActive) {
        AlertDialog(
            onDismissRequest = onDismiss,
            containerColor = Color.White,
            title = { Text("Add Workout", fontWeight = FontWeight.Bold) },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WorkoutInputField(
                        label = "Workout Title",
                        placeholder = "e.g. Morning Run",
                        value = state.title,
                        onValueChange = onTitleChange
                    )
                    WorkoutInputField(
                        label = "Type",
                        placeholder = "e.g. Cardio",
                        value = state.type,
                        onValueChange = onTypeChange
                    )
                    WorkoutInputField(
                        label = "Calories Burned",
                        placeholder = "e.g. 300",
                        value = state.calories,
                        onValueChange = onCaloriesChange,
                        keyboardType = KeyboardType.Number
                    )
                    Text("Choose Icon", fontWeight = FontWeight.Medium)
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(state.iconList) { icon ->
                            IconSelectionButton(
                                icon = icon.imageVector,
                                isSelected = state.selectedIcon.id == icon.id,
                                onClick = { onIconSelected(icon) }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onSave,
                            enabled = state.isFormValid,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6A6A6A),
                                disabledContainerColor = Color(0xFFBDBDBD)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Save Workout")
                        }
                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}
@Composable
fun WorkoutInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF2F2F7), RoundedCornerShape(10.dp))
                .border(1.dp, Color(0xFFE5E5EA), RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(placeholder, color = Color.Gray, fontSize = 16.sp)
                }
                innerTextField()
            }
        )
    }
}

@Composable
fun IconSelectionButton(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val corner = RoundedCornerShape(12.dp)

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFFF2F2F7), corner)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color(0xFF007AFF) else Color.Gray,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddWorkOutFormPreview() {
    AddWorkOutForm(
        isActive = true,
        state = WorkoutFormState(isFormValid = true),
        onDismiss = {},
        onSave = {},
        onTitleChange = {},
        onTypeChange = {},
        onCaloriesChange = {},
        onIconSelected = {}
    )
}