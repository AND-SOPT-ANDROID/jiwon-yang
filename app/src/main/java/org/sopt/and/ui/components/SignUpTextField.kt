package org.sopt.and.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//컴포넌트화
@Composable
fun SignUpTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    fieldType: String, //Email 혹은 Password로 전달 예정
    text: String,
    conditionCheck: Boolean,
    errMessage: String,
    placeholder: String,
    shouldShowPassword: Boolean = false,
    onPasswordVisibilityChange: () -> Unit = {},
    descriptionText: String = "",
){

    Column (
        modifier = modifier.fillMaxWidth()
    )
    {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.DarkGray
            ),
            placeholder = { Text(placeholder) },
            singleLine = true,
            visualTransformation = if (fieldType == "Password" && !shouldShowPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if (fieldType == "Password") {
                    val buttonLabel = if (shouldShowPassword) "hide" else "show"
                    Text(
                        text = buttonLabel,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onPasswordVisibilityChange() // 클릭 시 비밀번호 가시성 토글
                        }
                    )
                }
            }
        )

        if(descriptionText.isNotEmpty()){
            Text(
                text = descriptionText,
                color = Color.Gray,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

    if (!conditionCheck) {
        Text(
            text = errMessage,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}