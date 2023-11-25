package com.yofhi.contactapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.ui.ContactViewModel
import com.yofhi.contactapp.ui.MainViewModel
import com.yofhi.contactapp.ui.elements.About
import com.yofhi.contactapp.ui.elements.AddContact
import com.yofhi.contactapp.ui.elements.ContactList
import com.yofhi.contactapp.ui.elements.DetailContact
import com.yofhi.contactapp.ui.elements.EditContact


object MainDestinations {
    const val CONTACT_LIST = "contact-list"
    const val DETAIL_CONTACT = "detail-contact"
    const val EDIT_CONTACT = "edit-contact"
    const val ADD_CONTACT = "add-contact"
    const val ABOUT = "about"
    const val CONTACT_ID_KEY = "contactId"
    const val CONTACT_MODEL_KEY = "contact"
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun NavGraph(startDestination: String = MainDestinations.CONTACT_LIST) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.CONTACT_LIST) {
            val vm: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            ContactList(vm = vm, actions.addContact, actions.detailContact, actions.about)
        }

        composable(
            route = "${MainDestinations.DETAIL_CONTACT}/{${MainDestinations.CONTACT_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.CONTACT_ID_KEY)) {type = NavType.IntType })
        ) {
            val vm: ContactViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )

            val contactId = it.arguments?.getInt(MainDestinations.CONTACT_ID_KEY)
                ?: throw IllegalStateException("'contactId' shouldn't be null")

            vm.getContact(contactId)
            DetailContact(vm,actions.editContact, actions.backPress)
        }

        composable(
            route = "${MainDestinations.EDIT_CONTACT}/{${MainDestinations.CONTACT_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.CONTACT_ID_KEY)) {type = NavType.IntType })
        ) {
//            val vm = hiltNavGraphViewModel<ContactViewModel>(backStackEntry = it)
            val vm: ContactViewModel = hiltViewModel(it)


            val contactId = it.arguments?.getInt(MainDestinations.CONTACT_ID_KEY)
                ?: throw IllegalStateException("'contactId' shouldn't be null")

            vm.getContact(contactId)
            EditContact(vm, actions.backPress)
        }


        composable(
            route = "${MainDestinations.ADD_CONTACT}/{${MainDestinations.CONTACT_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.CONTACT_ID_KEY)) {type = NavType.IntType })
        ) {
//            val vm = hiltNavGraphViewModel<ContactViewModel>(backStackEntry = it)
            val vm: ContactViewModel = hiltViewModel(it)


            val contactId = it.arguments?.getInt(MainDestinations.CONTACT_ID_KEY)
                ?: throw IllegalStateException("'contactId' shouldn't be null")

            vm.getContact(contactId)
            AddContact(vm, actions.backPress)
        }

        composable(MainDestinations.ABOUT) {
            About(actions.backPress)
        }
    }
}

class MainActions(navController: NavHostController) {

    val addContact: (Contact) -> Unit = { contact: Contact ->
        navController.currentBackStackEntry?.arguments?.putInt(
            MainDestinations.CONTACT_MODEL_KEY,
            contact.id
        )
        navController.navigate("${MainDestinations.ADD_CONTACT}/${contact.id}")
    }

    val detailContact: (Contact) -> Unit = { contact: Contact ->
        navController.navigate("${MainDestinations.DETAIL_CONTACT}/${contact.id}")
    }

    val editContact: (Contact) -> Unit = { contact: Contact ->
        navController.navigate("${MainDestinations.EDIT_CONTACT}/${contact.id}")
    }

    val about: () -> Unit = {
        navController.navigate(MainDestinations.ABOUT)
    }

    val backPress: () -> Unit = {
        navController.navigateUp()
    }
}
