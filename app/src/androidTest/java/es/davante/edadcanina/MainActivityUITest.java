package es.davante.edadcanina;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void calcularEdad_muestraResultadoCorrecto() {
        // Introducir edad 6
        onView(withId(R.id.age_input))
                .perform(typeText("6"), closeSoftKeyboard());

        // Pulsar el botón Calcular
        onView(withId(R.id.btn_calc))
                .perform(click());

        // Verificar que el resultado sea visible y contenga el valor calculado (42 años)
        onView(withId(R.id.result_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Si tu perro fuera humano tendría 42 años")));
    }

    @Test
    public void botonLimpiar_vacíaTextoYOcultaResultado() {
        // Calcular primero
        onView(withId(R.id.age_input))
                .perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.btn_calc))
                .perform(click());

        // Pulsar botón Limpiar
        onView(withId(R.id.btn_clean))
                .perform(click());

        // Verificar que el EditText queda vacío y el resultado oculto
        onView(withId(R.id.age_input))
                .check(matches(withText("")));
        onView(withId(R.id.result_text))
                .check(matches(not(isDisplayed())));
    }
}
