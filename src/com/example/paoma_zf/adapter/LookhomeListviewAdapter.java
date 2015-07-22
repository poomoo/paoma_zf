package com.example.paoma_zf.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paoma_zf.R;

public class LookhomeListviewAdapter extends BaseAdapter {
	private List<Map<String, Object>> List = null;
	private Context context;
	// 用来导入布局
	private LayoutInflater inflater = null;
	private OnClickListener clickListener;

	public LookhomeListviewAdapter(List<Map<String, Object>> list,OnClickListener clickListener,
			Context context) {
		super();
		List = list;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
		this.clickListener=clickListener;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return List.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return List.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.style_lvlookhome, null);
			holder.moneyView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_money);
			holder.titleView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_title);
			holder.scoreView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_score);
			holder.infoView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_info);
			holder.roomtypeView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_roomtype);
			holder.positionView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_Position);
			holder.idView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_Id);
			holder.collectView = (TextView) convertView
					.findViewById(R.id.textView_stylelookhome_collect);
			holder.bgImageView = (ImageView) convertView
					.findViewById(R.id.imageView_stylelookhome_bg);
			holder.collectIamgeView = (ImageView) convertView
					.findViewById(R.id.imageView_stylelookhome_collect);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.moneyView.setText(List.get(position).get("currentPrice")
				.toString());
		holder.titleView.setText(List.get(position).get("name").toString());
		holder.scoreView.setText(List.get(position).get("avgScore").toString());
		holder.infoView.setText(List.get(position).get("totalOrdersDay")
				.toString());
		holder.roomtypeView.setText(List.get(position).get("roomType")
				.toString());
		holder.positionView.setText(List.get(position).get("addressDetails")
				.toString());
		holder.idView.setText(List.get(position).get("id").toString());
		holder.collectView
				.setText(List.get(position).get("collect").toString());

		if ((Boolean) List.get(position).get("collect")) {
			holder.collectIamgeView
					.setBackgroundResource(R.drawable.ic_collection_down);
			holder.collectIamgeView.setTag("true");
		} else {
			holder.collectIamgeView
					.setBackgroundResource(R.drawable.ic_collection_up);
			holder.collectIamgeView.setTag("false");
		}
		holder.bgImageView.setImageBitmap((Bitmap) (List.get(position)
				.get("headPic")));
		holder.collectIamgeView.setOnClickListener(clickListener);
		return null;
	}

	public static class ViewHolder {
		TextView moneyView, titleView, scoreView, infoView, roomtypeView,
				positionView, idView, collectView;
		ImageView collectIamgeView, bgImageView;
	}

}
