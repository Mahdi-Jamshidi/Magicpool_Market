package ir.magiccodes.magicpool.features.marketActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.magiccodes.magicpool.R
import ir.magiccodes.magicpool.apiManager.BASE_URL_IMAGE
import ir.magiccodes.magicpool.apiManager.model.CoinsData
import ir.magiccodes.magicpool.databinding.ItemRecyclerMarketBinding

class MarketAdapter(private val data : ArrayList<CoinsData.Data> , private val recyclerCallback: RecyclerCallback) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    lateinit var binding: ItemRecyclerMarketBinding

    inner class MarketViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bindViews(dataCoins: CoinsData.Data){

            binding.txtCoinFullName.text = dataCoins.coinInfo.fullName
            binding.txtCoinName.text = dataCoins.coinInfo.name
            binding.txtPrice.text = dataCoins.dISPLAY.uSD.pRICE

            val taghir = dataCoins.rAW.uSD.cHANGEPCT24HOUR
            if (taghir > 0){
                binding.txtTaghir.setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorGain))
                binding.txtTaghir.text = taghir.toString().substring(0, 4) + "%"
            }else if(taghir < 0){
                binding.txtTaghir.setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorLoss ))
                binding.txtTaghir.text = taghir.toString().substring(0, 5) + "%"

            }else {
                binding.txtTaghir.text = "0%"
            }

            val marketCap = dataCoins.rAW.uSD.mKTCAP / 1000000000
            val indexDot = marketCap.toString().indexOf(".")
            binding.txtMarketCap.text = "$" + marketCap.toString().substring(0, indexDot + 3) + " B"

            Glide
                .with(itemView)
                .load(BASE_URL_IMAGE + dataCoins.coinInfo.imageUrl)
                .into(binding.imgItem)

            itemView.setOnClickListener {
                recyclerCallback.onCoinItemClicked(dataCoins)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        binding = ItemRecyclerMarketBinding.inflate(inflate, parent, false)
        return MarketViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface RecyclerCallback{
        fun onCoinItemClicked(dataCoin: CoinsData.Data)
    }
}