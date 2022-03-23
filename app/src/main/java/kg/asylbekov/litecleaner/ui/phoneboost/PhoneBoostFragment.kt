package kg.asylbekov.litecleaner.ui.phoneboost

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import kg.asylbekov.litecleaner.R
import kg.asylbekov.litecleaner.base.BaseFragment
import kg.asylbekov.litecleaner.databinding.FragmentPhoneBoostBinding

class PhoneBoostFragment :
    BaseFragment<FragmentPhoneBoostBinding>(FragmentPhoneBoostBinding::inflate) {

    private var finalPercentage = 0

    override fun init() {
        super.init()
        initListeners()
        initRam()
        initAnim()
    }

    private fun initListeners() {
        binding.apply {
            ivPremium.setOnClickListener {
                Toast.makeText(context, "Functionality is in development ", Toast.LENGTH_SHORT)
                    .show()
            }
            ivGame.setOnClickListener {
                val url = "https://www.gamezop.com/?id=3178"
                val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
            }
            btnOptimize.setOnClickListener {
                Toast.makeText(context, "Functionality is in development ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initAnim() {

        binding.lottieAnim.playAnimation()
        binding.lottieAnim.loop(false)
//        if (finalPercentage >= 60) {
//            binding.lottieAnim.playAnimation()
//            binding.lottieAnim.loop(false)
//        } else {
//            binding.btnOptimize.setBackgroundResource(R.drawable.button_blue)
//            binding.lottieAnim.playAnimation()
//            binding.lottieAnim.loop(true)
//            binding.lottieAnim.cancelAnimation()
//
//        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRam() {
        val actManager = context?.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        val availMemory = memInfo.availMem.toDouble() / (1024 * 1024 * 1024)
        val totalMemory = memInfo.totalMem.toDouble() / (1024 * 1024 * 1024)
        val finalAvailMemory = availMemory.toString().substring(0, 4)
        val finalTotalMemory = totalMemory.toString().substring(0, 4)

        binding.ramAvailableSize.text = "${finalAvailMemory}" + " GB "
        binding.ramTotalSize.text = " / " + " ${finalTotalMemory}" + " GB "

        val percentage = (finalAvailMemory.toDouble() / finalTotalMemory.toDouble()) * 100
        finalPercentage = 100 - percentage.toInt()
        binding.tvPercentOfRam.text = "$finalPercentage%"
    }

    override fun onResume() {
        super.onResume()
        initRam()
        initAnim()
    }
}