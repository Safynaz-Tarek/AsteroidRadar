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
import com.udacity.asteroidradar.Repository.AsteroidFilter
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

        setHasOptionsMenu(true)

        viewModel.navigateToDetailScreen.observe(viewLifecycleOwner, Observer{ asteroid ->
            asteroid?.let {
                Navigation.findNavController(binding.root).navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onSetDetailNavigated()

            }
        })

        viewModel.isFilterApplied.observe(viewLifecycleOwner, Observer {
            if(it == true){
                adapter.submitList(viewModel.asteroidFilteredFeed)
                viewModel.doneFiltering()
                binding.asteroidRecycler.layoutManager?.scrollToPosition(0)
            }
        })

        viewModel.asteroidFeed.observe(viewLifecycleOwner, Observer { asteroidList ->
            if(viewModel.isFilterApplied.value == false){
                adapter.submitList(asteroidList)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getFilteredAsteroids(
            when(item.itemId){
                R.id.show_today_menu -> AsteroidFilter.TODAY
                R.id.show_week_menu -> AsteroidFilter.WEEK
                R.id.show_saved_menu -> AsteroidFilter.FAVORITE
                else -> AsteroidFilter.WEEK
            }
        )
        return true
    }
}
