# Godot 3 game engine Android plugin for Tapsell Ads


### Telegram: [https://t.me/Amani_0_1](https://t.me/Amani_0_1)

<br>

## برای دریافت نسخه سازگار با گودو 4 لطفاً در [تلگرام](https://t.me/Amani_0_1) پیام بدید.

<br>

### [View this page in English](README_EN.md)


<br>

<div dir="rtl">

## افزونه موتور بازی سازی گودو 3 برای نمایش تبلیغات تپسل

 
این پلاگین امکان استفاده از تبلغات آنی و جایزه‌ای   [تپسل](https://tapsell.ir) را در موتور بازی سازی [گودو](https://godotengine.org) فراهم میکند.
برای این کار کافی است فایل zip موجود در قسمت [*Release*](https://github.com/ygngy/godot-tapsell/releases) را دانلود کرده و محتوای آن را در پوشه **android/plugins** کپی کنید. نحوه استفاده از پلاگین اندروید در گودو را می توانید از [اینجا](https://docs.godotengine.org/en/stable/tutorials/plugins/android/android_plugin.html#loading-and-using-an-android-plugin) بخوانید.


**این پلاگین برای گودو نسخه 3.5.1 و تپسل پلاس نسخه 2.1.8 طراحی و تست شده است و ممکن است در سایر نسخه ها کارنکند.**


**این پلاگین فقط تبلغاتی که تمام صفحه هستند یعنی تبلیغات آنی و جایزه‌ای تپسل را نمایش می‌دهد.**


**تبلیغاتی که به صورت بنری در کنار بازی هستند مثلاً تبلیغات استاندارد و همسان در این پلاگین پشتیبانی نمی‌شود.**


**هنگام اکسپورت کردن فایل اندروید باید به اینترنت متصل باشید.**

<br>

## نحوه استفاده از پلاگین‌ها در اسکریپت گودو 

در اینجا یک نمونه از اسکریپت گودو برای استفاده از این افزونه آورده شده است. شما می توانید آن را مطابق با نیاز خود به صورت دلخواه تغییر دهید.

</div>

```python

var api_key = "COPY APP ID HERE" # کلید برنامه که باید از پرتال تپسل بگیرید

var tapsell_name = "Tapsell" # نام این پلاگین
var tapsell = null

func _ready():
	if Engine.has_singleton(tapsell_name):
		print("========== Tapsell found =============")
		tapsell = Engine.get_singleton(tapsell_name)
		tapsell.connect("tapsell_init_succeed", self, "on_tapsell_init_succeed") # ()
		tapsell.connect("tapsell_init_failed", self, "on_tapsell_init_failed")   # (errorDomain, errorMessage)
		tapsell.connect("tapsell_request_succeed", self, "on_tapsell_request_succeed") # (zone, advertise_id)
		tapsell.connect("tapsell_request_failed", self, "on_tapsell_request_failed") # (zone, errorMessage)
		tapsell.connect("tapsell_show_opened", self, "on_tapsell_show_opened") # (advertise_id)
		tapsell.connect("tapsell_show_closed", self, "on_tapsell_show_closed") # (advertise_id)
		tapsell.connect("tapsell_show_rewarded", self, "on_tapsell_show_rewarded") # (advertise_id)
		tapsell.connect("tapsell_show_error", self, "on_tapsell_show_error") # (advertise_id, errorMessage)
		tapsell.initialize(api_key, true) # پارامتر اول کلید برنامه و پارامتر دوم نمایش یا عدم نمایش لاگ‌ها را مشخص می‌کند
		# استفاده کنید true برای مشاهده لاگ‌ها می‌توانید از
		# استفاده کنید false ولی در نسخه نهایی از
	
	else:
		print("========= Error! Tapsell plugin NOT FOUND ===========")


func on_tapsell_init_succeed():
	# در اینجا اتصال به تپسل موفقیت آمیز بوده است
	# پس از اتصال موفقیت آمیز می‌توانید درخواست تبلیغ کنید
	# را باید از پرتال تپسل دریافت کنید zone_id
	print("on_tapsell_init_succeed")
	# برای درخواست تبلیغ جایزه‌ای از متد زیر استفاده کنید
	tapsell.requestRewardedVideo("GET ZONE_ID FROM TAPSELL")
	# برای درخواست تبلیغ آنی از متد زیر استفاده کنید
	tapsell.requestInterstitial("GET ZONE_ID FROM TAPSELL")
	
func on_tapsell_init_failed(errorDomain, errorMessage):
	# در اینجا اتصال به تپسل ناموفق بوده است
	print("on_tapsell_init_failed domain: " + errorDomain + " msg: " + errorMessage)
	
func on_tapsell_request_succeed(zone, id):
	# در اینجا درخواست تبلیغ موفقیت آمیز بوده است
	# پس از موفقیت آمیز بودن درخواست تبلیغ می‌توانید درخواست نمایش تبلیغ را بدهید
	# برای کاهش تاخیر در نمایش تبلیغ زودتر درخواست تبلیغ را بدهید تا تبلیغ دانلود شود
	# آیدی تبلیغ را ذخیره کنید و در زمان مناسب درخواست نمایش آن را بدهید
	print("on_tapsell_request_succeed ad_id: " + id + " zone: " + zone)
	# بفهمید zone, id نوع تبلیغ را باید از طریق
	# در صورتی که تبلیغ جایزه‌ای باشد با متد زیر آن را نمایش بدهید
	tapsell.showRewardedVideo(id)
	# در صورتی که تبلیغ آنی باشد از طریق متد زیر آن را نمایش دهید
	tapsell.showInterstitial(id)

func on_tapsell_request_failed(zone, msg):
	# در اینجا درخواست تبلیغ ناموفق بوده است
	print("on_tapsell_request_failed msg: " + msg + " zone: " + zone)
	
func on_tapsell_show_opened(id):
	# در اینجا تبلیغ روی صفحه باز شده است
	print("on_tapsell_show_opened ad_id: " + id)	
	
func on_tapsell_show_closed(id):
	# در اینحا تبلیغ بسته شده است
	print("on_tapsell_show_closed ad_id: " + id)	
	
func on_tapsell_show_rewarded(id):
	# در اینجا ویدیوی تبلیغی به صورت کامل مشاهده شده است
	# پس در این متد باید جایزه مشاهده تبلیغ را به کاربر بدهید
	print("on_tapsell_show_rewarded ad_id: " + id)	
	
func on_tapsell_show_error(id, msg):
	# در اینجا در نمایش تبلیغ خطایی رخ داده است
	print("on_tapsell_show_error: " + msg + " ad_id: " + id)		


```

<br>

## برای دریافت نسخه سازگار با گودو 4 لطفاً در [تلگرام](https://t.me/Amani_0_1) پیام بدید.

<br>

---------------------------------------------------------------------------


**"Mohamadreza Amani"**  

My LinkedIn Profile: [https://www.linkedin.com/in/ygngy](https://www.linkedin.com/in/ygngy)

My Github Profile: [https://github.com/ygngy](https://github.com/ygngy)  

Email:  [amany1388@gmail.com](mailto:amany1388@gmail.com)

Telegram: [https://t.me/Amani_0_1](https://t.me/Amani_0_1)