package ir.magiccodes.magicpool.apiManager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinAboutItem(
    var coinWebsite: String? = "no_data",
    var coinGithub: String? = "no_data",
    var coinTwitter: String? = "no_data",
    var coinReddit: String? = "no_data",
    var coinDesc: String? = "no_data"
): Parcelable