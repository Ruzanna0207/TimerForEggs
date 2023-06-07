package com.example.timerforeggs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.timerforeggs.databinding.ChooseEggsTypeBinding

private lateinit var adapterChooseTypeEggs: AdapterChooseTypeEggs
private val list = listOf(
    EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/41/1488371_69845nothumb500.jpg",
        "Яйцо сварено в мешочек:\n" +
                "белок затвердел только по внешнему краю, остальная часть жидкая, как и желток.",
        "3 минуты", 3
    ),
    EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/43/1488373_74120nothumb500.jpg",
        "Яйцо в мешочек:\n" +
                "белок почти сварился, но немного жидкий, как и желток.",
        "5 минут", 5
    ), EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/44/1488374_86006nothumb500.jpg",
        "Яйцо сварено всмятку:\n" +
                "Белок сварен полностью, а желток жидкий.",
        "7 минут", 7
    ), EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/45/1488377_71060nothumb500.jpg",
        "Яйцо вкрутую:\n" +
                "белок сварился полностью, желток схватился, но остался в середине мягким.",
        "10 минут", 10
    ), EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/46/1488379_66448nothumb500.jpg",
        "Вкрутую:\n" +
                "белок и желток полностью сварены. Оптимально для длительного хранения и добавления в салаты.",
        "14 минут", 14
    ), EggsInfo(
        "https://www.povarenok.ru/data/cache/2016mar/09/48/1488381_92951nothumb500.jpg",
        "Переваренное яйцо:\n" +
                "белок и желток полностью сварены, но начинают терять во вкусовых качествах, становятся постепенно резиновыми.",
        "20 минут", 20
    )
)

 class ChooseEggsType : Fragment(), Clickable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ChooseEggsTypeBinding.inflate(inflater)

        adapterChooseTypeEggs = AdapterChooseTypeEggs(this, list) //создание адаптера со слушателем нажатий
        binding.recyclerView.adapter = adapterChooseTypeEggs
        binding.recyclerView.layoutManager!!.onSaveInstanceState()
        adapterChooseTypeEggs.notifyDataSetChanged()

        return binding.root
    }

    companion object {
        fun newInstance() = ChooseEggsType()
    }

     override fun onClick(time: Int) { //обработка действий для слушателя нажатий в адаптере
         val secondFragment = TimerForEggs()
         val bundle = Bundle().apply {
             putInt("time", time)
         }
         secondFragment.arguments = bundle

         requireActivity().supportFragmentManager.beginTransaction()
             .replace(R.id.frame, secondFragment)
             .addToBackStack(null)
             .commit()
     }
 }