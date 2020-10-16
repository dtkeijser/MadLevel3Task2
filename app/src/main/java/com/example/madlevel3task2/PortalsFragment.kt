package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals){ onPortalClick(it) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPortals.layoutManager= GridLayoutManager(activity,2)
        rvPortals.adapter = portalAdapter
        //Some initial portals to check
        if (portals.isEmpty()) {
            portals.apply {
                add(Portal("Nu", "http://nu.nl"))
                add(Portal("YouTube", "http://youtube.nl"))

            }
            observerAddPortalResult()
    }

}
    /**
     * Use Custom Tabs to launch a web activity with the portal url.
     * Note: It's important that the Url starts with http:// or https://
     */
    private fun onPortalClick(portal: Portal) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();

        // Launch url if it's a valid url.
        if (URLUtil.isValidUrl(portal.url)) customTabsIntent.launchUrl(
            requireContext(),
            Uri.parse(portal.url)
        )
        else Toast.makeText(context, "Invalid Url: ${portal.url}", Toast.LENGTH_SHORT).show()
    }

    private fun observerAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.title, it.url)

                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal text!")

        }
    }


}