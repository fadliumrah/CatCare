package com.fadli.catcare.ui.auth

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fadli.catcare.data.viewmodel.AuthViewModel
import com.fadli.catcare.data.viewmodel.SettingViewModel
import com.fadli.catcare.data.viewmodel.ViewModelGeneralFactory
import com.fadli.catcare.data.viewmodel.ViewModelSettingFactory
import com.fadli.catcare.R
import com.fadli.catcare.databinding.FragmentLoginBinding
import com.fadli.catcare.utils.Constanta
import com.fadli.catcare.utils.Helper
import com.fadli.catcare.utils.SettingPreferences
import com.fadli.catcare.utils.dataStore


class LoginFragment : Fragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }

    private var viewModel: AuthViewModel? = null
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SettingPreferences.getInstance((activity as AuthActivity).dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelSettingFactory(pref))[SettingViewModel::class.java]
        viewModel = ViewModelProvider(
            this,
            ViewModelGeneralFactory((activity as AuthActivity))
        )[AuthViewModel::class.java]
        viewModel?.let { vm ->
            vm.loginResult.observe(viewLifecycleOwner) { login ->
                // success login process triggered -> save preferences
                settingViewModel.setUserPreferences(
                    login.loginResult.token,
                    login.loginResult.userId,
                    login.loginResult.name,
                    viewModel!!.tempEmail.value ?: Constanta.preferenceDefaultValue
                )
            }
            vm.error.observe(viewLifecycleOwner) { error ->
                if (error.isNotEmpty()) {
                    Helper.showDialogInfo(requireContext(), error)
                }
            }
            vm.loading.observe(viewLifecycleOwner) { state ->
                binding.loading.root.visibility = state
            }
        }
        settingViewModel.getUserPreferences(Constanta.UserPreferences.UserToken.name)
            .observe(viewLifecycleOwner) { token ->
                // token changes -> redirect to Main Activity
                if (token != Constanta.preferenceDefaultValue) (activity as AuthActivity).routeToMainActivity()
            }
        binding.btnAction.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()
            when {
                email.isEmpty() or password.isEmpty() -> {
                    Helper.showDialogInfo(
                        requireContext(),
                        getString(R.string.UI_validation_empty_email_password)
                    )
                }
                !email.matches(Constanta.emailPattern) -> {
                    Helper.showDialogInfo(
                        requireContext(),
                        getString(R.string.UI_validation_invalid_email)
                    )
                }
                password.length <= 6 -> {
                    Helper.showDialogInfo(
                        requireContext(),
                        getString(R.string.UI_validation_password_rules)
                    )
                }
                else -> {
                    viewModel?.login(email, password)
                }
            }
        }
        binding.btnRegister.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.container, RegisterFragment(), RegisterFragment::class.java.simpleName)
                /* shared element transition to main activity */
                addSharedElement(binding.labelAuth, "auth")
                addSharedElement(binding.edEmail, "email")
                addSharedElement(binding.edPassword, "password")
                addSharedElement(binding.containerMisc, "misc")
                commit()
            }
        }
    }

}