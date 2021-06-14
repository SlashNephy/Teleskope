package blue.starry.teleskope.components.statusbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import blue.starry.teleskope.api.ServiceInfo

@Composable
fun ServiceStatusBarItem(
    services: List<ServiceInfo>,
    currentService: ServiceInfo?,
    isFetching: Boolean,
    onSelect: (ServiceInfo) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selected = currentService?.name ?: if (isFetching) "(データ取得中...)" else "(サービスなし)"

    Text(
        text = selected,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .clickable(onClick = { expanded = true })
            .border(BorderStroke(1.dp, Color.Black))
            .padding(horizontal = 20.dp)
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        for (service in services) {
            DropdownMenuItem(
                onClick = {
                    onSelect(service)
                    expanded = false
                }
            ) {
                Text(text = service.name)
            }
        }
    }
}
