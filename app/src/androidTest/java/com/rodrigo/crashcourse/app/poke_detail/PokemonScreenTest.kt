package com.rodrigo.crashcourse.app.poke_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rodrigo.crashcourse.helpers.PokemonDtoHelper
import com.rodrigo.crashcourse.utils.PokemonTestTags
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class PokemonScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val pokemon = PokemonDtoHelper.fakePokemonDto("pikachu")


    @Test
    fun whenStateIsSuccess_pokemonNameIsDisplayed() {
        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = PokemonState.Success(pokemon),
                onBack = {}
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.NAME)
            .assertIsDisplayed()
    }

    @Test
    fun when_back_button_clicked_onBack_is_called() {
        //var backClicked = false
        var backClicked by mutableStateOf(false)
        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = PokemonState.Success(pokemon),
                onBack = { backClicked = true }
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.BACK)
            .assertIsDisplayed()
            .performClick()

        assertTrue(backClicked)
    }

    @Test
    fun when_back_clicked_screen_changes_state() {
        var state by mutableStateOf<PokemonState>(
            PokemonState.Success(
                PokemonDtoHelper.fakePokemonDto("pikachu")
            )
        )

        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = state,
                onBack = {
                    state = PokemonState.Idle
                }
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.BACK)
            .performClick()

        composeTestRule
            .onNodeWithText("Waiting...")
            .assertIsDisplayed()
    }

    @Test
    fun when_state_is_error_error_text_is_displayed() {
        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = PokemonState.Error("Algo salió mal"),
                onBack = {}
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.ERROR)
            .assertIsDisplayed()
    }

    @Test
    fun when_state_is_loading_loading_indicator_is_displayed() {
        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = PokemonState.Loading,
                onBack = {}
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun when_state_is_idle_waiting_text_is_displayed() {
        composeTestRule.setContent {
            PokemonScreen(
                pokemonState = PokemonState.Idle,
                onBack = {}
            )
        }

        composeTestRule
            .onNodeWithTag(PokemonTestTags.IDLE)
            .assertIsDisplayed()
    }
}

