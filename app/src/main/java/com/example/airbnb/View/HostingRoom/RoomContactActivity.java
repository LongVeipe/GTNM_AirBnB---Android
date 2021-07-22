package com.example.airbnb.View.HostingRoom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.airbnb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomContactActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_contact);
        ButterKnife.bind(this);
        initToolbar();
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Cho thuê Phòng/Nhà");
        }
    }
    private  void initOptions ()
    {
        String []country_options = {"United States", "Canada", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and/or Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecudaor", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France, Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kosovo", "Kuwait", "Kyrgyzstan", "Lao People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfork Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbarn and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States minor outlying islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City State", "Venezuela", "Vietnam", "Virigan Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zaire", "Zambia", "Zimbabwe" };
        String []province_options = {"Bắc Giang", "Bắc Kạn", "Cao Bằng", "Hà Giang", "Lạng Sơn", "Phú Thọ", "Quảng Ninh", "Thái Nguyên", "Tuyên Quang", "Lào Cai", "Yên Bái","Điện Biên", "Hòa Bình", "Lai Châu", "Sơn La", "Bắc Ninh", "Hà Nam", "Hải Dương", "Hưng Yên", "Nam Định", "Ninh Bình", "Thái Bình", "Vĩnh Phúc", "Hà Nội", "Hải Phòng","Hà Tĩnh", "Nghệ An", "Hồ Chí Minh City"};
        String []district_options = {"Quận 1","Quận 2", "Quận 3","Quận 4","Quận 5", "Quận 6","Quận 7","Quận 8", "Quận 9","Quận 10","Quận 11", "Quận 12","Quận Bình Tân", "Quận Bình Thạnh", "Quận Gò Vấp", "Quận Phú Nhuận", "Quận Tân Bình", "Quận Tân Phú", "Quận Thủ Đức", "Huyện Bình Chánh", "Huyện Cần Giờ", "Huyện Củ Chi", "Huyện Hóc Môn", "Huyện Nhà Bè"};
        String []bank_options = {"Ngân Hàng TMCP Đông Nam Á (SeABank)", "Ngân hàng TMCP An Bình (ABBANK)", "Ngân hàng TMCP Bắc Á (BacABank)", "Ngân hàng TMCP Bản Việt (VietCapitalBank)", "Ngân hàng TMCP Hàng hải Việt Nam (MSB)", "Ngân hàng TMCP Kỹ Thương Việt Nam (Techcombank)", "Ngân hàng TMCP Kiên Long (KienLongBank)", "Ngân hàng TMCP Nam Á (Nam A Bank)", "Ngân hàng TMCP Quốc Dân (NCB)", "Ngân hàng TMCP Việt Nam Thịnh Vượng (VPBank)", "Ngân hàng TMCP Phát triển Thành phố Hồ Chí Minh (HDBank)", "Ngân hàng TMCP Phương Đông (OCB)", "Ngân hàng TMCP Quân đội (MB)", "Ngân hàng TMCP Đại chúng (PVcombank)", "Ngân hàng TMCP Quốc tế Việt Nam (VIB)", "Ngân hàng TMCP Sài Gòn (SCB)"};

    }
}