# Object oriented design patterns and Code refactoring experiment
Software engineering lab @ SUT

اعضای تیم:
- همراز عرفاتی ۹۹۱۰۹۷۹۹
- امیرحسین براتی ۹۹۱۰۱۳۰۸
- امیرحسین عابدی ۹۹۱۰۵۵۹۴

## فاز ۱

در ابتدا تست‌های برنامه را مینویسیم که با اپروچ TDD شروع به توسعه کد کنیم. به یک کلاس command نیاز داریم که تمامی فانکشنالیتی ‌های خواسته شده را پیاده سازی کند. این فانکشنالیتی‌ها عبارتند از distance, which, addHatedCity, toggleDirectional با استفاده از این توابع می‌توانیم هرچیزی که نیاز داریم را پیاده کنیم.

![img.png](img.png)

حال توضیح می‌دهیم که از State و Strategy در چه بخش‌هایی از برنامه استفاده کرده‌ایم. کلاس Router وظیفه پیدا کردن کمترین مسیر بین ۲ نود را دارد. از دیزاین پترن Strategy استفاده می‌کنیم که ۲ پیاده‌سازی مختلف از این کلاس داشته باشیم و همینطور خود کلاس Router هم یک کلاس abstract است که تابع route در آن هیچ پیاده‌سازی‌ای ندارد. بنابراین با استفاده از کلاس‌های BusRouter و TrainRouter خواسته مسئله را انجام می‌دهیم.

![img_1.png](img_1.png)
![img_2.png](img_2.png)

از دیزاین پترن State برای تاگل کردن بین Bidirectional و Unidirectional استفاده می‌کنیم. کلاس Router درون خودش یک فیلد state دارد که نشان‌ می‌دهد در حال حاضر دو طرفه است یا یک طرفه. هرکدام از این استیت‌ها یک پیاده‌سازی درون خود دارند که با کال کردن آن‌ها جهت درون یال‌ها فعال می‌شود و یا از بین می‌رود.

![img_3.png](img_3.png)
![img_4.png](img_4.png)

در انتها کافی است که در کلاس Main از کلاس Command استفاده کنیم و گراف را از یوزر بگیریم. همینطور می‌توانیم ببینیم که تست‌هایی که نوشته‌ایم به درستی کار می‌کنند.

![img_5.png](img_5.png)




## فاز ۲
در این فاز قصد داریم که در ابتدا ۲ مورد دیزاین پترن Facade را میخواهیم پیاده‌سازی کنیم. در ابتدا می‌بینیم که کلاس CodeGenerator توابع زیادی را expose کرده است و کلاس Parser که از این کلاس استفاده می‌کند از تعداد خیلی کمی از این توابع استفاده می‌کند. برای همین یک شی از نوع CodeGeneratorFacade می‌سازیم که فانکشنالیتی‌های لازم را برای کلاس Parser محیا می‌کند.

![telegram-cloud-photo-size-4-5913335192909627994-y](https://github.com/user-attachments/assets/48e84264-8c77-435d-ac7c-820e8dff435a)

از این کلاس در Parser استفاده می‌کنیم. همینطور مشاهده می‌کنیم که در کلاس SymbolTable از کلاس Memory استفاده شده است اما از بین توابع زیادی که این کلاس ارائه می‌دهد فقط یکی از آن‌ها استفاده می‌شود. بنابراین یک MemoryFacade هم برای استفاده در کلاس SymbolTable می‌سازیم.

![telegram-cloud-photo-size-4-5913335192909627982-y](https://github.com/user-attachments/assets/ad9baace-6b9b-4cf0-9cc4-ca505a2830ec)

حال که دو مورد Facade را پیاده‌سازی کردیم یک کلاس با الگوی Strategy هم پیاده‌سازی می‌کنیم. در کلاس SymbolTable می‌بینیم که یک switch-statement داریم که فاکشنالیتی‌های مختلف کلاس action را پیاده‌سازی می‌کند. این switch-statement می‌تواند توسط دیزاین پترن Strategy بهبود یابد. کلاس Action را به صورت یک کلاس ابسترکت در می‌آوریم و همینطور enum act را حذف می‌کنیم. به جای اینکار ۳ پیاده‌سازی مختلف از Action خواهیم داشت که کار switch-statement زیر را می‌کنند.

<img width="1068" alt="image" src="https://github.com/user-attachments/assets/32b36f6e-2381-4a58-83db-dfe190a60bbd">

۳ نوع Action با نام‌های AcceptAction, ReduceAction, ShiftAction داریم که کارهای خواسته شده را درون خودشان و در تابع act که در کلاس پدر وجود دارد پیاده می‌کنند. همینطور بعد از این کار تابع ToString هم پیاده‌سازی جدایی توسط هر ۳ کلاس دارد. در نهایت می‌توانیم با استفاده از این دیزاین پترن کل تصویر بالا را به یک خط زیر خلاصه کنیم.

```java
currentAction.act(this);
```

حال می‌خواهیم یک مورد separate query from modifier را اجرا کنیم. در کلاس Memory دو تابع getter با نام‌های getTemp و getDataAddress وجود دارند که علاوه بر وظیفه کوئری، آپدیت دیتا را هم انجام می‌دهند. برای بهبود کد دو تابع incrementTemp و incrementDataAddress اضافه می‌کنیم که جداگانه و هروقت که لازم بود این اعداد زیاد بشوند صدا زده می‌شوند.

<img width="689" alt="image" src="https://github.com/user-attachments/assets/a27b8b69-4790-4e9d-8905-9f655ddb84db">

در ادامه تکینک self encapsulated field را پیاده‌سازی میکنیم. در کلاس LexicalAnalyzer یک فیلد از نوع Matcher وجود دارد که همیشه بدون گتر و ستر از آن استفاده شده است. در ابتدا برای این فیلد ستر و گتر تعریف می‌کنیم و هرجا به مقدار آن نیاز داشتیم از گتر و هرکجا که نیاز داشتیم آن را ست کنیم از ستر استفاده می‌کنیم.

<img width="960" alt="image" src="https://github.com/user-attachments/assets/a525436e-a1b4-4d6b-a041-286f0254da2e">

در مورد از ما خواسته شده است که ۲ تکنیک دلخواه را پیاده‌سازی کنیم. این ۲ تکنیک Hide method و Encapsulate collection هستند. در اولی توابع public که توسط یک کلاس ارائه می‌شوند اما توسط هیچ کلاسی غیر از خودشان استفاده نمی‌شود می‌توانند به private تغییر کنند. این توابع در کلاس CodeGenerator بسیار زیاد هستند و فقط در خود کلاس استفاده می‌شوند. بنابراین signature این توابع را به private تغییر می‌دهیم.

![telegram-cloud-photo-size-4-5915586992723313901-y](https://github.com/user-attachments/assets/d0bb85b3-1b03-46a3-a962-0817e312e68d)

تکنیک دوم به این صورت کار می‌کند که اگر یک کلاس فیلدی از نوع کالکشن دارد نباید صرفا برای آن گتر و ستر داشته باشد، بلکه باید متود‌های Add, remove و getter داشته باشد به طوری که getter شی از نوع unmodifiable برگرداند. در پیاده‌سازی فعلی در کلاس Parser کالکشنی از نوع Stack<Integer> داریم که گتر دارد و از بیرون قابل تغییر است. برای جلوگیری از این کار برای این کالکشن توابع Pop, Head, Push را پیاده‌سازی می‌کنیم.

<img width="710" alt="image" src="https://github.com/user-attachments/assets/5111253c-fc71-47d4-862c-eedc4b1a1ef5">

حال پلاگین formatter را به پروژه اضافه می‌کنیم و با اجرای کامند mvn formatter:format تمامی فایل‌های پروژه را فرمت می‌کنیم.

<img width="1390" alt="image" src="https://github.com/user-attachments/assets/bba1ed83-115c-42ad-b200-43921e18f4cf">

همانطور که مشخص است ۲۷ تا از ۳۵ تا فایل فرمت شده‌اند که در کامیت formatter:format فایل دیدن هستند. (فایل‌هایی که در پروژه graph قرار داشتند پوش نشدند.)







## سوالات


