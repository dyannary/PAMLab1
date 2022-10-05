package com.example.pamlab1
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.pamlab1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

import com.example.pamlab1.databinding.FragmentFirstBinding

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notification = findViewById<Button>(R.id.notification)

      //  val service = CounterNoificationService(applicationContext)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notification.setOnClickListener {
           // service.showNotification(Counter.value)
            val notificationChannel = NotificationChannel(
                "ChannelId",
                "ChannelName",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(notificationChannel)

            var builder = Notification
                .Builder(this, "123")
                .setSmallIcon(R.drawable.ic_baseline_all_inclusive_24)
                .setContentTitle("My notification")
                .setContentText("HELLO")

            notificationManager.notify(13, builder.build())
        }


        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val searchWord = findViewById<EditText>(R.id.searchWord)

        buttonSearch.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=${searchWord.text}")
            )
            startActivity(urlIntent)

           //
        // findViewById<TextView>(R.id.textView).text = searchWord.text
        }

        val btn_camera = findViewById<Button>(R.id.btn_camera)

        btn_camera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }


    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}



















