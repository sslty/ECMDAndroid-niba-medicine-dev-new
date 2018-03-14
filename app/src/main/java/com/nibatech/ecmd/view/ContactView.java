package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.contact.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactView extends LinearLayout implements SideBar.OnTouchingLetterChangedListener, SectionIndexer {
    private Activity activity;
    private final ListView lvContact;
    private final SideBar sbContact;
    private final TextView tvContact;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList = new ArrayList<>();
    private boolean showCenterLetter;
    private ContactListener contactListener;

    public ContactView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_sidebar_index, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContactView);
        try {
            showCenterLetter = typedArray.getBoolean(R.styleable.ContactView_showCenterLetter, true);
        } finally {
            typedArray.recycle();
        }
        lvContact = (ListView) findViewById(R.id.lv_sidebar);
        sbContact = (SideBar) findViewById(R.id.sb_sidebar);
        tvContact = (TextView) findViewById(R.id.tv_sidebar);

        contactAdapter = new ContactAdapter();
        lvContact.setAdapter(contactAdapter);
        lvContact.setOnScrollListener(contactAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (contactListener != null) {
                    contactListener.onItemClick(contactList, position);
                }
            }
        });

        if (showCenterLetter) {
            sbContact.setTextView(tvContact);
        }
        sbContact.setOnTouchingLetterChangedListener(this);
    }

    public void setContactListener(ContactListener contactListener) {
        this.contactListener = contactListener;
    }


    public void addContacts(List<Contact> contactList) {
        if (contactList == null || contactList.size() == 0) {
            return;
        }
        this.contactList = contactList;
        Collections.sort(this.contactList);

        Set<String> set = new HashSet<>();
        for (Contact contact : this.contactList) {
            set.add(String.valueOf(contact.getFirstChar()));
        }

        sbContact.setSideBarLetter(set);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        int position = getPositionForSection(s.charAt(0));
        if (position != -1) {
            lvContact.setSelection(position);
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < contactList.size(); i++) {
            char firstChar = contactList.get(i).getFirstChar();
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        Contact item = contactList.get(position);
        return item.getFirstChar();
    }

    class ContactAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
        public final static String CONTACT_ITEM = "contact_item";


        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (null == convertView) {
                convertView = View.inflate(activity, R.layout.list_contact, null);
                viewHolder = new ViewHolder();
                viewHolder.headItemView = (HeadItemView) convertView.findViewById(R.id.headItemView);
                viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.contact_catalog);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.headItemView.setPicassoTag(CONTACT_ITEM)
                    .setHeadViewImageAndGender(contactList.get(position).getDoctorProfile().getAvatarUrl(),
                            contactList.get(position).getDoctorProfile().getGender(),
                            contactList.get(position).getDoctorProfile().getFullName())
                    .showEmptyTopView()
                    .setHospital(contactList.get(position).getDoctorProfile().getDoctorType())
                    .setIntentData(contactList.get(position).getDoctorProfile().getHomepageUrl(), BroadCast.REFRESH_FRIENDS_CONTACT)
                    .showVIP(true);


            //如果是第0个那么一定显示#号
            if (position == 0) {
                viewHolder.tvLetter.setVisibility(View.VISIBLE);
                viewHolder.tvLetter.setText("" + contactList.get(position).getFirstChar());
            } else {
                //如果和上一个item的首字母不同，则认为是新分类的开始
                Contact prevData = contactList.get(position - 1);
                if (contactList.get(position).getFirstChar() != prevData.getFirstChar()) {
                    viewHolder.tvLetter.setVisibility(View.VISIBLE);
                    viewHolder.tvLetter.setText("" + contactList.get(position).getFirstChar());
                } else {
                    viewHolder.tvLetter.setVisibility(View.GONE);
                }
            }

            return convertView;
        }

        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            Picasso picasso = Picasso.with(activity);
            if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                picasso.resumeTag(CONTACT_ITEM);
            } else {
                picasso.pauseTag(CONTACT_ITEM);
            }

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }

    }

    class ViewHolder {
        HeadItemView headItemView;
        TextView tvLetter;
    }

    public interface ContactListener {
        void onItemClick(List<Contact> contactList, int position);
    }

}