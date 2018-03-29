package com.fakturku.aplikasi.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.fakturku.aplikasi.R
import com.fakturku.aplikasi.ui.fragment.InvoiceFragment
import com.fakturku.aplikasi.ui.fragment.costumerFragment.CostumerListFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import android.content.Intent
import com.fakturku.aplikasi.ui.activity.login.LoginActivity
import com.fakturku.aplikasi.ui.fragment.productFragment.ProductFragment
import com.fakturku.aplikasi.ui.fragment.supplierFragment.SupplierFragment


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displaySelectedPage(item.itemId)
        return true
    }

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var actionBar : ActionBar
    private lateinit var currentFragment : Fragment
    private lateinit var lastFragment : Fragment
    private var mPendingRunnable : Runnable? = null
    private val mHandler : Handler = Handler()

    private lateinit var strUserId : String
    private var isNavigationActive : Boolean = false
    private var isFirstVisit : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //Set Dashboard UI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dashboardToolbar.elevation = 4.0f
        }
        setSupportActionBar(dashboardToolbar)
        actionBar = this@DashboardActivity.supportActionBar!!

        toggle = object : ActionBarDrawerToggle(
                this@DashboardActivity,
                dashboardDrawerLayout,
                dashboardToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                // If mPendingRunnable is not null, then add to the message queue
                if(mPendingRunnable != null){
                    mHandler.post(mPendingRunnable)
                    mPendingRunnable = null
                }
            }
        }
        dashboardDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        dashboardNavView.setNavigationItemSelectedListener(this@DashboardActivity)

        //Fragment backstack management
        supportFragmentManager.addOnBackStackChangedListener {
            if(supportFragmentManager.backStackEntryCount > 0){
                val fragments = supportFragmentManager.fragments
                lastFragment = fragments[fragments.size-1]
                //setNavMenuItemCheck(lastFragment.javaClass.simpleName)
            }else{
                unCheckAllMenuItems()
                val fragments = supportFragmentManager.fragments
                lastFragment = fragments[0]
            }
        }

        //add this line to display menu1 when the activity is loaded
        val strFragmentName :String? = intent.getStringExtra(KEY_DASHBOARD_FRAGMENT)
        if(strFragmentName != null){
            //TODO : use when need to navigate
        }else{
            displaySelectedPage(R.id.nav_invoice)
        }

        // If mPendingRunnable is not null, then add to the message queue
        if(mPendingRunnable != null){
            mHandler.post(mPendingRunnable)
            mPendingRunnable = null
        }
    }

    override fun onBackPressed() {
        if(dashboardDrawerLayout.isDrawerOpen(GravityCompat.START)){
            dashboardDrawerLayout.closeDrawer(GravityCompat.START)
        }else{
            if(isNavigationActive){
                if(supportFragmentManager.backStackEntryCount > 0){
                    super.onBackPressed()
                }else{
                    AlertDialog.Builder(this@DashboardActivity)
                            .setMessage(R.string.dashboard_dialog_exit_message)
                            .setPositiveButton(R.string.dashboard_dialog_exit_positive,{ _ , _ ->
                                finish()
                            })
                            .setNegativeButton(R.string.dashboard_dialog_exit_negative, null)
                            .show()
                }
            }else{
                super.onBackPressed()
            }
        }
    }

    private fun displaySelectedPage(itemId :Int){
        when(itemId){
            //MAIN MENU
            R.id.nav_invoice ->{
                showDrawer()
                actionBar.setTitle(R.string.invoice_title)
                currentFragment = InvoiceFragment()
                goToFragment()
            }
            R.id.nav_customer ->{
                showDrawer()
                actionBar.setTitle(R.string.costumer_title)
                currentFragment = CostumerListFragment()
                goToFragment()
            }
            R.id.nav_product ->{
                showDrawer()
                actionBar.setTitle(R.string.product_title)
                currentFragment = ProductFragment()
                goToFragment()
            }
            R.id.nav_supplier ->{
                showDrawer()
                actionBar.setTitle(R.string.supplier_title)
                currentFragment = SupplierFragment()
                goToFragment()
            }
            R.id.nav_cost ->{
                showDrawer()
                //actionBar.setTitle(R.string.report_fragment_title)
                //currentFragment = ReportFragment()
                goToFragment()
            }
            R.id.nav_report ->{
                showDrawer()
                //actionBar.setTitle(R.string.report_fragment_title)
                //currentFragment = ReportFragment()
                goToFragment()
            }

            //OPTION
            R.id.nav_settings ->{
                //TODO : create settings
            }
            R.id.nav_help ->{
                //TODO : create help
            }
            R.id.nav_logout ->{
                logout()
            }

        }
        dashboardDrawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun unCheckAllMenuItems(){
        val menu : Menu = dashboardNavView.menu
        (0 until menu.size())
                .map { menu.getItem(it) }
                .forEach {
                    if(it.hasSubMenu()){
                        val submenu: SubMenu = it.subMenu
                        (0 until submenu.size())
                                .map { submenu.getItem(it) }
                                .forEach { it.isChecked = false }
                    }else{
                        it.isChecked = false
                    }
                }
    }

    private fun goToFragment(){
        mPendingRunnable = Runnable {
            //replacing the fragment
            if(isFirstVisit){
                isFirstVisit = false
                lastFragment = currentFragment
                supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.dashboardContentFrame, currentFragment)
                        .commit()
            }else{
                if(currentFragment.javaClass.simpleName != lastFragment.javaClass.simpleName){
                    supportFragmentManager.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.dashboardContentFrame, currentFragment,
                                    currentFragment.javaClass.simpleName)
                            .addToBackStack(lastFragment.javaClass.simpleName)
                            .commit()
                }
            }
        }
    }

    private fun logout(){
        AlertDialog.Builder(this)
                .setMessage("Logout dari akun Anda?")
                .setPositiveButton("Ya") { _, _ ->
                    clearLoginToken()
                }
                .setNegativeButton("Tidak", null)
                .show()
    }

    private fun clearLoginToken() {
        val pref = applicationContext.getSharedPreferences("LoginPref", MODE_PRIVATE)
        if(pref.contains("LoginToken")){
            pref.edit().clear().apply()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intentLogin = Intent(this@DashboardActivity, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()
    }

    fun showDrawer(){
        isNavigationActive = true
        toggle.isDrawerIndicatorEnabled = true
        actionBar.setDisplayHomeAsUpEnabled(false)
        toggle.syncState()
        dashboardToolbar.setNavigationOnClickListener {
            dashboardDrawerLayout.openDrawer(GravityCompat.START)
        }
        dashboardDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    fun hideDrawer(){
        isNavigationActive = false
        toggle.isDrawerIndicatorEnabled = false
        actionBar.setDisplayHomeAsUpEnabled(false)
        dashboardDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun showBackButton(){
        isNavigationActive = true
        toggle.isDrawerIndicatorEnabled = false
        actionBar.setDisplayHomeAsUpEnabled(true)
        dashboardToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        dashboardDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun setTitleBar(title :Int){
        actionBar.setTitle(title)
    }

    fun getUserId() : String{
        return strUserId
    }

    fun showKeyboard(showKeyboard: Boolean){
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus!!.windowToken,
                        if (showKeyboard) InputMethodManager.SHOW_FORCED
                        else InputMethodManager.HIDE_NOT_ALWAYS )
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo?
        activeNetworkInfo = connectivityManager.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    companion object {
        val KEY_DASHBOARD_FRAGMENT :String = "KeyDashboardFragment"
    }
}
