package com.example.medreminder.presentation.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medreminder.R
import com.example.medreminder.component.animations.RotatingShapeAnimation
import com.example.medreminder.ui.theme.GoogleSansFlex

@OptIn(ExperimentalTextApi::class)
@Composable
fun OnboardScreen(viewModel: OnBoardViewModel = hiltViewModel()){

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text("You won't forget", modifier = Modifier.fillMaxWidth().padding(start = 20.dp)
        , color = Color.Black, fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold, fontFamily = GoogleSansFlex)
        Text("your MED", modifier = Modifier.fillMaxWidth().padding(start = 20.dp)
            , color = Color.Black, fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold, fontFamily = GoogleSansFlex)

        Spacer(modifier = Modifier.weight(0.5f))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            RotatingShapeAnimation(modifier = Modifier.size(280.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.welcome_preparing_subtitle),
                fontFamily = GoogleSansFlex,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = {
                    viewModel.onNextClick()
                },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,

                    ),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Next",
                        fontFamily = GoogleSansFlex,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1
                    )
                }
            }
        }


    }



}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoard(){
    OnboardScreen()
}