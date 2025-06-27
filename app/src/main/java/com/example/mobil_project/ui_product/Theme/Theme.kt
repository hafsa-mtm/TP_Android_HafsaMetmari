import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Purple80 = Color(0xFFD0BCFF)
private val PurpleGrey80 = Color(0xFFCCC2DC)
private val Pink80 = Color(0xFFEFB8C8)

private val Purple40 = Color(0xFF6650a4)
private val PurpleGrey40 = Color(0xFF625b71)
private val Pink40 = Color(0xFF7D5260)

private val CrystalPurplePalette = darkColorScheme(
    primary = Color(0xFF9C27B0),
    secondary = Color(0xFF7B1FA2),
    tertiary = Color(0xFFBA68C8),
    surface = Color(0xFF121212),
    surfaceVariant = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE1E1E1),
    background = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFE1E1E1),
    error = Color(0xFFCF6679)
)

@Composable
fun YourAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        CrystalPurplePalette
    } else {
        lightColorScheme(
            primary = Color(0xFF9C27B0),
            secondary = Color(0xFF7B1FA2),
            tertiary = Color(0xFFBA68C8),
            surface = Color.White,
            surfaceVariant = Color(0xFFF3E5F5),
            onSurface = Color.Black,
            background = Color(0xFFF5F5F5),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.White,
            onBackground = Color.Black,
            error = Color(0xFFB00020)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, // Using your custom typography
        content = content
    )
}