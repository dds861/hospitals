package com.dd.hospitals.ui.search

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.dd.domain.model.HospitalModel
import com.dd.hospitals.R
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(private val context: Context,
                    private val adapterType: SearchState.AdapterType,
                    override val listItems: MutableList<HospitalModel> = mutableListOf(),
                    private val itemListener: (HospitalModel) -> Unit) : EmaRecyclerAdapter<HospitalModel>() {
    override val layoutItemId: Int = getLayoutItemId()

    private fun getLayoutItemId(): Int {
        return when (adapterType) {
            SearchState.AdapterType.HINT -> R.layout.item_search
            SearchState.AdapterType.MAKALS -> R.layout.item_card
        }
    }

    override fun View.bind(item: HospitalModel, viewType: Int) {
        when (adapterType) {
            SearchState.AdapterType.HINT -> {
                tvText.text = item.address

                rootView.setOnClickListener {
                    itemListener.invoke(item)
                }
            }

            SearchState.AdapterType.MAKALS -> {
                tvSubTitle.text = item.branch
                tvPhone.text = item.phone
                tvAddress.text = item.address
                tvTitle.text = item.region

                itemListener.invoke(item)

                ivCopy.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(ivCopy)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(ivCopy)

                    copyToClipboard("Адрес: ${item.address},\n" +
                            "Отдел: ${item.branch},\n" +
                            "Телефон: ${item.phone},\n" +
                            "График: ${item.region}"
                    )
                }
                ivShare.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(ivShare)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(ivShare)

                    shareText("Адрес: ${item.address},\n" +
                            "Отдел: ${item.branch},\n" +
                            "Телефон: ${item.phone},\n" +
                            "График: ${item.region}"
                    )
                }
                ivWhatsApp.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(ivWhatsApp)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(ivWhatsApp)

                    onClickWhatsApp("Адрес: ${item.address},\n" +
                            "Отдел: ${item.branch},\n" +
                            "Телефон: ${item.phone},\n" +
                            "График: ${item.region}"
                    )
                }

                tvPhone.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(tvPhone)
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(tvPhoneText)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(tvPhone)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(tvPhoneText)
                    item.phone?.let { it1 -> dialPhone(it1) }
                }

                tvPhoneText.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(tvPhone)
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(tvPhoneText)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(tvPhone)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(tvPhoneText)
                    item.phone?.let { it1 -> dialPhone(it1) }
                }

                ivLocation.setOnClickListener {
                    YoYo.with(Techniques.FadeOut).duration(150).repeat(0).playOn(ivLocation)
                    YoYo.with(Techniques.FadeIn).duration(350).repeat(0).playOn(ivLocation)
                    item.address?.let { it1 -> shareLocation(it1) }
                }
            }
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("Makal text", text)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(context, R.string.TextCopied, Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, text)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(sharingIntent, context.resources.getString(R.string.share_using)))
    }

    private fun dialPhone(text: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$text")
        context.startActivity(intent)
    }

    private fun onClickWhatsApp(text: String) {
        val pm: PackageManager = context.packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            val info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp")
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(Intent.createChooser(waIntent, "Share with"))
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun shareLocation(text: String) {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$text"))
        context.startActivity(intent)
    }
}