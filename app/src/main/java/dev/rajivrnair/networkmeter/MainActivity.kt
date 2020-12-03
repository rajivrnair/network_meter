package dev.rajivrnair.networkmeter

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.rajivrnair.networkmeter.databinding.ActivityMainBinding
import dev.rajivrnair.networkmeter.models.Network
import dev.rajivrnair.networkmeter.services.NetworkMonitor
import dev.rajivrnair.networkmeter.views.NetworksListAdapter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var mService: NetworkMonitor
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNetworksList: RecyclerView
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: NetworksListAdapter
    private var mNetworks: List<Network> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNetworksList()
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        binding.toolbar.inflateMenu(R.menu.toolbar_actions)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter_wifi -> {
                    binding.scanAnimation.visibility = VISIBLE
                    showNetworks(showWifi = true, showGsm = false)
                    true
                }

                R.id.action_filter_gsm -> {
                    binding.scanAnimation.visibility = VISIBLE
                    showNetworks(showWifi = false, showGsm = true)
                    true
                }

                R.id.action_about -> {
                    val dialogView = LayoutInflater.from(this).inflate(R.layout.about, null)
                    val builder = AlertDialog.Builder(this)
                        .setView(dialogView)
                        .setTitle(getString(R.string.about_title))
                        .setCancelable(true)
                        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    builder.show()
                    true
                }

                R.id.action_rescan -> {
                    binding.scanAnimation.visibility = VISIBLE
                    showNetworks(showWifi = true, showGsm = true)
                    true
                }

                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun setupNetworksList() {
        mNetworksList = binding.networksList
        mLinearLayoutManager = LinearLayoutManager(this)
        mNetworksList.layoutManager = mLinearLayoutManager
        mAdapter = NetworksListAdapter(mNetworks)
        mNetworksList.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        object : CountDownTimer(3000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                // do nothing
            }

            override fun onFinish() {
                showNetworks(showWifi = true, showGsm = true)
            }
        }.start()
    }

    private fun showNetworks(showWifi: Boolean, showGsm: Boolean) {
        mNetworks = mService.getNetworks(showWifi, showGsm, applicationContext)
        mAdapter.updateList(mNetworks)
        if(mNetworks.isEmpty()) {
            Toast.makeText(this, "No networks found!", Toast.LENGTH_SHORT).show()
            return
        }
        binding.scanAnimation.visibility = GONE
    }
}