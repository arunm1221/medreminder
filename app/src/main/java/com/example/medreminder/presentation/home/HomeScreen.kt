package com.example.medreminder.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medreminder.ui.theme.GoogleSansFlex
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onAddMedicine: () -> Unit,
    onAddEvent: () -> Unit,
    onLogout: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {

    val today = LocalDate.now()
    val weekStart = today.minusDays(today.dayOfWeek.value.toLong()-1)
    val weekDays = (0..6).map { weekStart.plusDays(it.toLong()) }

    val bottomSheetState = rememberModalBottomSheetState (skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable() { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showBottomSheet=true},
                shape = RoundedCornerShape(16.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                contentColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add"
                )
            }
        }
    ){ innerPadding->

        Column(
            modifier = Modifier.fillMaxSize().
            padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),


        ) {

            //TopBar
            TopBar(onLogout = { viewModel.logout(onLogout) })
            Spacer(modifier = Modifier.height(20.dp))
            WeekDaysRow(today,weekDays)
            Spacer(modifier = Modifier.height(20.dp))
            BlankSpace()
            if (showBottomSheet){
                ModalBottomSheet(
                    onDismissRequest = {},
                    sheetState = bottomSheetState,
                    shape = RoundedCornerShape(8.dp)

                ){

                    AddBottomSheetOption(onAddMedicine,onAddEvent)

                }
            }

        }

    }


}

@Composable
fun AddBottomSheetOption(onAddMedicine: () -> Unit, onAddEvent: () -> Unit) {

    Column(modifier = Modifier.fillMaxWidth().navigationBarsPadding()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Add new",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp, start = 4.dp)
        )
        SheetOption(
            icon = Icons.Outlined.MedicalServices,
            label = "Medicine",
            description = "Add a medication reminder",
            onClick = onAddMedicine
        )
        Spacer(modifier = Modifier.height(8.dp))
        SheetOption(
            icon = Icons.Outlined.Event,
            label = "Event",
            description = "Add a calendar event",
            onClick = onAddEvent
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun SheetOption(
    icon: ImageVector,
    label: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {

            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}


        @OptIn(ExperimentalTextApi::class)
        @Composable
fun BlankSpace() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "No events for today",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
                fontFamily = GoogleSansFlex
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekDaysRow(today: LocalDate, weekDays: List<LocalDate>) {

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp)) {

        Text(text = today.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault()))
        LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            items(weekDays){
                DayItem(it, isSelected = it==today)
            }


        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayItem(date: LocalDate, isSelected: Boolean) {

    val dayLetter = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).take(1)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clip(
            shape = CircleShape
        ).background(
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            ).size(48.dp),
        verticalArrangement = Arrangement.Center) {

        Text(text = dayLetter, style = MaterialTheme.typography.labelSmall
        , color = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black)

        Text(text = date.dayOfMonth.toString(), style = MaterialTheme.typography.labelSmall
            , color = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Black)

    }
    
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TopBar(onLogout: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Medicine", fontFamily = GoogleSansFlex, fontSize = 20.sp, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        IconButton(onClick = {}) {
            Icon(modifier = Modifier.size(35.dp).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(16.dp)).padding(4.dp),
                imageVector = Icons.Filled.Edit, contentDescription = "edit")
        }
        IconButton(onClick = {}) {
            Icon(modifier = Modifier.size(35.dp).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(16.dp)).padding(4.dp),
                imageVector = Icons.Filled.CalendarMonth, contentDescription = "calendar")
        }
        IconButton(onClick = {}) {
            Icon(modifier = Modifier.size(35.dp).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(16.dp)).padding(4.dp),
                imageVector = Icons.Filled.Settings, contentDescription = "settings")
        }
        IconButton(onClick = onLogout) {
            Icon(modifier = Modifier.size(35.dp).background(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(16.dp)).padding(4.dp),
                imageVector = Icons.Filled.Logout, contentDescription = "logout")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview() {
    TopBar()
}