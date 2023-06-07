package com.example.timerforeggs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.timerforeggs.databinding.TimerForEggsBinding
import kotlinx.coroutines.*

val scopeOne = CoroutineScope(Dispatchers.Default)

class TimerForEggs : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingTwo = TimerForEggsBinding.inflate(inflater)

        val timer = arguments?.getInt("time") ?: 0 //получение данных при помощи объекта типа Bundle

        var minutes = timer
        var seconds = timer / 60

        bindingTwo.timerMinutes.text = "Осталось минут: $minutes"
        bindingTwo.timerSeconds.text = "Осталось секунд: $seconds"

        bindingTwo.buttonTimerStart.setOnClickListener {
            val coroutineForTimerTwo = scopeOne.launch(Dispatchers.Default) { //запуск корутины для отсчета таймера
                while (minutes > 0 || seconds > 0) {
                    delay(1000L)
                    if (seconds == 0) {
                        minutes--
                        seconds = 59
                    } else {
                        seconds--
                    }

                    withContext(Dispatchers.Main) {  // передача на main поток информации для отображения в UI
                        bindingTwo.timerMinutes.text = "Осталось минут: $minutes"
                        bindingTwo.timerSeconds.text = "Осталось секунд: $seconds"
                    }
                }
            }

            if (coroutineForTimerTwo.isActive) { //условия появления для кнопок СТАРТ и СТОП
                bindingTwo.buttonTimerStart.visibility = View.GONE
                bindingTwo.buttonTimerStop.visibility = View.VISIBLE
                bindingTwo.timerSeconds.visibility = View.VISIBLE
            }

            bindingTwo.buttonTimerStop.setOnClickListener {
                coroutineForTimerTwo.cancel() // отмена корутины

                requireActivity().supportFragmentManager // возвращение в предыдйщий фрагмент
                    .beginTransaction()
                    .replace(R.id.frame, ChooseEggsType.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return bindingTwo.root
    }
}
