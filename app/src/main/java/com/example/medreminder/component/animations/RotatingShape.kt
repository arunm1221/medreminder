package com.example.medreminder.component.animations

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.R

@Composable
fun RotatingShapeAnimation(modifier: Modifier= Modifier){
    val infiniteTransition = rememberInfiniteTransition("infinite_rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween (20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background

    Box(modifier=modifier, Alignment.Center) {

        Icon(painter = painterResource(R.drawable.ic_ten_sided_cookie),
            contentDescription = null ,
            tint = primaryColor,
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation)
        )

        Icon(painter = painterResource(R.drawable.ic_launcher_monochrome),
            contentDescription = null ,
            modifier = Modifier.size(250.dp),
            tint = backgroundColor
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRotatingShapeAnimation(){
    RotatingShapeAnimation(modifier = Modifier)
}