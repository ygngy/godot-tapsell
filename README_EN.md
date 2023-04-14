# Godot game engine Android plugin for Tapsell Ads

<br>

### [مشاهده این صفحه به زبان فارسی](README.md)


<br>

With the help of this plugin, you can add [Tapsell](https://tapsell.ir) ads to games made with the [Godot](https://godotengine.org) game engine. For this, just download the zip file in the [Release](https://github.com/ygngy/godot-tapsell/releases) section and copy its content to the **android/plugins** folder. You can read how to use the Android plugin in Godot [here](https://docs.godotengine.org/en/stable/tutorials/plugins/android/android_plugin.html#loading-and-using-an-android-plugin).


**This plugin only displays fullscreen ads i.e. Rewarded/Interstitial ads.**


**Native and Standard ads which is displayed as banner are not supported in this plugin. (Only fullscreen ads are supported.)**
 

**You must be connected to the Internet when exporting the Android APK.**
<br>

## How to use this plugin in Godot script

Here is a sample godot script for using this plugin. You may use it in another way as your needs.

```python

var api_key = "COPY APP ID HERE" # API key for your app (Get it from tapsell.ir)

var tapsell_name = "Tapsell" # Name of this plugin 
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
		tapsell.initialize(api_key, true) # The first parameter is api key and the second specifies whether or not to display the logs
		# Use true to debug and view the logs, but in the final release version use false
	
	else:
		print("========= Error! Tapsell plugin NOT FOUND ===========")


func on_tapsell_init_succeed():
	# Tapsell connection has been successful
	# After successful connection you can request for advertisements
	# You must get zone_id from Tapsell portal
	print("on_tapsell_init_succeed")
	# Use the method below to request Rewarded ads
	tapsell.requestRewardedVideo("GET ZONE_ID FROM TAPSELL")
	# Use the following method to request Interstitial ads
	tapsell.requestInterstitial("GET ZONE_ID FROM TAPSELL")
	
func on_tapsell_init_failed(errorDomain, errorMessage):
	# The connection to Tapsall has failed
	print("on_tapsell_init_failed domain: " + errorDomain + " msg: " + errorMessage)
	
func on_tapsell_request_succeed(zone, id):
	# The ad request has been successful
	# After the ad request is successful, you can request to display the ad
	# To reduce the delay in displaying the ad, request the ad earlier so that the ad can be downloaded
	# Save the ad ID and request to display it at the right time
	print("on_tapsell_request_succeed ad_id: " + id + " zone: " + zone)
	# Find out the ad type through zone and id 
	# If the ad type is "Rewarded", display it using the method below
	tapsell.showRewardedVideo(id)
	# If the ad type is "Interstitial", display it using the method below
	tapsell.showInterstitial(id)

func on_tapsell_request_failed(zone, msg):
	# The ad request has failed
	print("on_tapsell_request_failed msg: " + msg + " zone: " + zone)
	
func on_tapsell_show_opened(id):
	# The ad is showing on the screen
	print("on_tapsell_show_opened ad_id: " + id)	
	
func on_tapsell_show_closed(id):
	# The ad is closed
	print("on_tapsell_show_closed ad_id: " + id)	
	
func on_tapsell_show_rewarded(id):
	# The Ad video has been viewed completely
	# So, in this method, you have to give the reward for viewing the ad to the user
	print("on_tapsell_show_rewarded ad_id: " + id)	
	
func on_tapsell_show_error(id, msg):
	# There was an error in displaying the ad here
	print("on_tapsell_show_error: " + msg + " ad_id: " + id)	
	

```


---------------------------------------------------------------------------


**"Mohamadreza Amani"**  

My LinkedIn Profile: [https://www.linkedin.com/in/ygngy](https://www.linkedin.com/in/ygngy)

My Github Profile: [https://github.com/ygngy](https://github.com/ygngy)  

Email:  [amany1388@gmail.com](mailto:amany1388@gmail.com)