package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = MainAdapter(AsteroidListener { asteroidId ->
            viewModel.onAsteroidClicked(asteroidId)
        })
        binding.asteroidRecycler.adapter = adapter
        var test_aster : Asteroid =  Asteroid(1, "AA", "12", 1.1,2.2,3.3,4.4,true )
        var test_aster2 : Asteroid =  Asteroid(2, "BB", "13", 10.10,20.20,30.30,40.40,false )
        var data_test : List<Asteroid> = listOf(test_aster, test_aster2)

        adapter.submitList(data_test)
        setHasOptionsMenu(true)

        viewModel.navigateToDetailScreen.observe(viewLifecycleOwner, Observer{ asteroid ->
            asteroid?.let {
                Navigation.findNavController(binding.root).navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onSetDetailNavigated()

            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
