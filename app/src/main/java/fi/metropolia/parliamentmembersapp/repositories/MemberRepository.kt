package fi.metropolia.parliamentmembersapp.repositories

import fi.metropolia.parliamentmembersapp.network.MembersDatabase

object MemberRepository {
    fun getParties(): List<String> = MembersDatabase.getInstance().membersDAO.getAllParties()
}