package simostudio96.speengriddy.hubapp.data.repository

import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.data.model.Puzzle

class PuzzleRepository {
    private val puzzles: List<Puzzle> = listOf(
        Puzzle(
            id = 1,
            texts = mapOf("en" to "Shrimp Ramen", "de" to "Garnelen-Ramen", "es" to "Ramen con gambas", "fr" to "Ramen aux crevettes", "hi" to "झींगा रामेन", "id" to "Ramen Udang", "pt" to "Ramen de camarão", "ar" to "رامن بالروبيان"),
            resId = R.drawable.ramen_bowl
        ),
        Puzzle(
            id = 2,
            texts = mapOf("en" to "Garden Salad", "de" to "Gartensalat", "es" to "Ensalada fresca", "fr" to "Salade fraîche", "hi" to "ताज़ा सलाद", "id" to "Salad Segar", "pt" to "Salada fresca", "ar" to "سلطة طازجة"),
            resId = R.drawable.garden_salad
        ),
        Puzzle(
            id = 3,
            texts = mapOf("en" to "Coffee Trio", "de" to "Kaffee-Trio", "es" to "Trío de café", "fr" to "Trio de café", "hi" to "कॉफ़ी तिकड़ी", "id" to "Trio Kopi", "pt" to "Trio de café", "ar" to "ثلاثي القهوة"),
            resId = R.drawable.coffee_cups
        ),
        Puzzle(
            id = 4,
            texts = mapOf("en" to "Chocolate Cupcakes", "de" to "Schoko-Cupcakes", "es" to "Cupcakes de chocolate", "fr" to "Cupcakes au chocolat", "hi" to "चॉकलेट कपकेक", "id" to "Cupcake Cokelat", "pt" to "Cupcakes de chocolate", "ar" to "كب كيك بالشوكولاتة"),
            resId = R.drawable.chocolate_cupcakes
        ),
        Puzzle(
            id = 5,
            texts = mapOf("en" to "Golden Croissants", "de" to "Goldene Croissants", "es" to "Cruasanes dorados", "fr" to "Croissants dorés", "hi" to "सुनहरे क्रोइसां", "id" to "Kroisan Keemasan", "pt" to "Croissants dourados", "ar" to "كرواسون ذهبي"),
            resId = R.drawable.croissant_pile
        ),
        Puzzle(
            id = 6,
            texts = mapOf("en" to "Fruit Market", "de" to "Obstmarkt", "es" to "Mercado de frutas", "fr" to "Marché aux fruits", "hi" to "फलों का बाज़ार", "id" to "Pasar Buah", "pt" to "Mercado de frutas", "ar" to "سوق الفاكهة"),
            resId = R.drawable.fruit_market
        ),
        Puzzle(
            id = 7,
            texts = mapOf("en" to "Sushi Platter", "de" to "Sushi-Platte", "es" to "Bandeja de sushi", "fr" to "Plateau de sushis", "hi" to "सुशी थाली", "id" to "Hidangan Sushi", "pt" to "Bandeja de sushi", "ar" to "طبق سوشي"),
            resId = R.drawable.sushi_platter
        )
    )

    fun getPuzzles(): List<Puzzle> = puzzles

    fun findById(id: Int): Puzzle? = puzzles.firstOrNull { it.id == id }
}
