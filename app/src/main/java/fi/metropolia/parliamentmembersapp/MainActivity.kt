package fi.metropolia.parliamentmembersapp
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import fi.metropolia.parliamentmembersapp.databinding.ActivityMainBinding
import fi.metropolia.parliamentmembersapp.network.MemberApi
import fi.metropolia.parliamentmembersapp.network.MembersDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Main activity with implementation for the navigation drawer and the CoroutineScope. I am aware
//that GlobalScope is not particularly good for this scope but I'm yet to find a better way to
//implement the coroutine.

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(
            Dispatchers.IO,
            CoroutineStart.DEFAULT
        ) {
            val members = MemberApi.retrofitService.getMembersList()
            members.forEach {
                MembersDatabase.getInstance().membersDAO.insert(it)
            }
        }

//        hamburger drawer implementation
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as
                NavHostFragment
        val navController = navHostFragment.navController

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.partyFragment -> navController.navigate(R.id.partyFragment)
                R.id.nav_drawer_item2 -> navController.navigate(R.id.aboutFragment)
            }
            true
        }
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}