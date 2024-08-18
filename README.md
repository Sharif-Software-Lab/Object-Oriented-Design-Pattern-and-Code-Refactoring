# Object oriented design patterns and Code refactoring experiment
Software engineering lab @ SUT

اعضای تیم:
- همراز عرفاتی ۹۹۱۰۹۷۹۹
- امیرحسین براتی ۹۹۱۰۱۳۰۸
- امیرحسین عابدی ۹۹۱۰۵۵۹۴

## فاز ۱

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







## سوالات


