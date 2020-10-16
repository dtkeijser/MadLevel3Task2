package com.example.madlevel3task2

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etBtn.setOnClickListener { addPortal() }
    }

    private fun addPortal(){
        if (validateInput()){
            val portal = Portal(etTitle.text.toString(), etURL.text.toString())
            //set the data as fragmentResult, we are listening for REQ_REMINDER_KEY in RemindersFragment!
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))

            //"pop" the backstack, this means we destroy
            //this fragment and go back to the PortalFragment
            findNavController().popBackStack()
        }
    }

    /**
     * check if input fields are valid
     * return true if valid els return false
     */
    private fun validateInput(): Boolean {
        if (etTitle.text.toString().isBlank() || etURL.text.toString().isBlank())
        {
            Toast.makeText(requireContext(), "Please fill in the title and url.",Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Patterns.WEB_URL.matcher(etURL.text.toString()).matches()){
            Toast.makeText(requireContext(),"URL is not valid", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}