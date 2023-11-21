package br.com.fiap.hr_tech.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.hr_tech.R

enum class TypeMessage(
    val code: Int,
    private val descriptionResId: Int,
    private val textColorResId: Int,
    private val backgroundColorResId: Int,
) {
    INFO(1, R.string.info, R.color.blue, R.color.light_blue),
    WARNING(2, R.string.warning, R.color.orange, R.color.light_yellow),
    ERROR(3, R.string.error, R.color.red, R.color.light_red),
    SUCCESS(4, R.string.success, R.color.green, R.color.light_green);

    @Composable
    fun getDescription(): String {
        return LocalContext.current.getString(descriptionResId)
    }

    @Composable
    fun getTextColor(): Color {
        return Color(LocalContext.current.getColor(textColorResId))
    }

    @Composable
    fun getBackgroundColor(): Color {
        return Color(LocalContext.current.getColor(backgroundColorResId))
    }
}
