[Useful links and plugins](/Useful_things.md) 😉

# Homework progress and deadline:
#### Don't hesitate to ask your mentor to actualize document.
https://docs.google.com/spreadsheets/d/1cMIg0OQsFTpISKxCy6Zlni_V2iDcj9CCzytG3qtckkY/edit?usp=sharing

# Training chat in Telegram:
https://t.me/joinchat/DNzoqBZ3iHcGMtD4CzR5fw

# Homework flow:
* Issues should be created in this repository!
* Make homework in separate branch with following format: homework_DD.MM_theme_name (*homework_28.02_clean_code*)
* Open pull request in you repository and provide link in issue.
* Add screenshots whe you done some UI changes

# Feel free to create issue if some links don't work or you need some help

# Architecture presentation:

https://docs.google.com/presentation/d/1Eb94Dv-Te9wGGDmdm7GJGTEJt_UNOGRr6WNzC9EMmag/edit?usp=sharing

# 08.04.2019 this task is optional
* Write own AsyncTask that will have the same API as android.AsyncTask, but will support screen rotation. If we already  downloaded data, we just need to reuse it, if activity was destroyed we need to destoy cache data.
* Write home screen of your app which will load data from few places in parallel mode and combine them in screen

https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a
https://developer.android.com/guide/components/processes-and-threads.html?hl=en
https://developer.android.com/topic/performance/threads?hl=en
https://developer.android.com/reference/android/os/AsyncTask
https://developer.android.com/guide/components/loaders
https://developer.android.com/reference/android/support/v4/app/LoaderManager.html
https://developer.android.com/reference/android/os/ResultReceiver
Review Sync collections
https://habr.com/ru/company/luxoft/blog/157273/

# 28.04.2019
Patterns => http://citforum.ru/SE/project/pattern/

SOLID => https://ru.wikipedia.org/wiki/SOLID

KISS => https://ru.wikipedia.org/wiki/KISS_(%D0%BF%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF)

YAGNI => https://ru.wikipedia.org/wiki/YAGNI

DRY => https://ru.wikipedia.org/wiki/Don%E2%80%99t_repeat_yourself

# HW 24.04.2019
Gradle: https://developer.android.com/studio/build
examples provided in /gradle branch of this project 

Homework:
- In your or some demo project use at least 4 buildVariants (using flavors and build type). Use different logic for each.
- Using modules in your projects is additional plus

# HW 18.04.2019
Kotlin: https://kotlinlang.ru/

Book: Kotlin in Action (Ru or EN)

Slides: https://docs.google.com/presentation/d/16ZdYrWNw9p0B5LHGAkcVW6wquiXM2pbR17c5aRWZ88o/edit?usp=drivesdk

Kotlin cheats 😉: [Kotlin Cheat Sheet](assets/Kotlin_Cheat_Sheet.pdf).

Homework:
Make all exercises in `kotlinKoans` module and make sure that all test passes successfully.

The flow as usial: you make changes in separate branch and then open pull request + issue

# HW 14.03.2019

Related documentation:
- https://developer.android.com/training/animation/screen-slide
- https://developer.android.com/guide/topics/ui/layout/recyclerview
- https://developer.android.com/reference/android/support/v7/widget/DividerItemDecoration
- https://developer.android.com/reference/android/support/v7/util/DiffUtil
- https://developer.android.com/reference/android/support/v7/widget/DefaultItemAnimator
- https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper

## Theory
- Take a look at the related documentation and learn items you didn't understand during the lesson.
- Find out how to use https://developer.android.com/reference/android/arch/paging/PagedListAdapter. Do not use it in HW practics!

## Practics
- Create compound view wih custom attributes for student UI item
- Create Backend data <-> RecyclerView UI relations (you can fork lesson implementation and finish it, or use your own).
Backend data could be mocked, but it should give possibility to pull, delete and edit entities.
RecyclerView should support drag'n'drop, swipe-to-delete and pagination features

# HW 11.03.2019

Compound view:
https://code.tutsplus.com/tutorials/creating-compound-views-on-android--cms-22889

Navigation Drawer:
https://developer.android.com/training/implementing-navigation/nav-drawer#java

You need to create `Activity` with Navigation Drawer and two or more `Fragments` which you will switch after clicking in NavDrawer.

Header is a compound view with Vector icon, User name, and email. 
Icon should be clickable and each click will change icon color (can be random color or predifined list)

# HW 06.03.2019
More about Android Views: 

sp, dp, px: https://startandroid.ru/ru/11-pamjatka/40-edinitsy-izmerenija-chem-otlichaetsja-dp-dip-ot-px-screen-density.html

Android Layouts:

https://o7planning.org/en/10423/android-ui-layouts-tutorial

https://habr.com/ru/post/181820/

https://developer.android.com/guide/topics/ui/declaring-layout?hl=ru

Dark mode: https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94

Homework you can find inside 👉 [doc](assets/android_ui.md).

# HW 04.03.2019

Remember - answer on almost every question you can ask is covered at https://developer.android.com/.
If you don't understand what is service - you go to https://developer.android.com/guide/components/services.
If you forgot what is BroadcastReceiver - you go to https://developer.android.com/guide/components/broadcasts and so on.
In case items described too complicated, read usual articles on native language and then go back to the offical docs.

## Theory
- Read what is foreground service.
- Read about service/broadcast limitations on new Android versions and what is LocalBroadcastManager.
- Read how to handle long-running operation using BroadcastReceiver.
- Other ways to manage back-stack - activity attributes (f.i. taskAffinity, allowTaskReparenting) and intent flags (f.i. FLAG_ACTIVITY_CLEAR_TOP)

## Practics
- Write simple Fragment which restores its state.
- Write Activity, Service and BroadcastReceiver. Service sends data, Activity receives using Broadcast and shows it (show using Toast or logs, if you have no experience in UI). Preferable to execute at least one long-running operation, but it's optional.

## Optional for self-development
- Create custom Application class, which will provide singleton instances (any custom classes).
- Create you own implementation of IntentService.

# HW 28.02.2019
- Familiarize with code style guide.

[Presentation: Clean Code](https://docs.google.com/presentation/d/1IS_aoc5RUhsLZKBqJW0JPkKnBpUE7I5fOG6sJkn7rbk/edit?usp=sharing "Clean Code")

[More about: Clean Code [RU]](https://medium.com/clean-code-channel/understanding-clean-code-in-android-3f00301fe16e "Clean Code")


## Part 1:
- you will work in repository with Calculator from interview:  
- make separate branch for refactoring (like *homework_28.02_clean_code*)
- align code with code style guidelines
- push changes and open pull request in your repository (from your branch to master)
- create issue with link to your pull request and mention your **Mentor**.

## Part 2:
- fork training repository
- make separate branch for refactoring
- make some changes and after that make sure that all tests are successful
- push changes and open pull request in training repository (from your branch to master)
- add link for pull request to your previous issue

The best homework request will get to the repository 😎

# HW 25.02.2019
Read about:
- Object basic methods: hashCode(), equals(), toString(), etc.
- Collections: List, Set, Map, etc.
- Generic Methods, Wildcards.
- StringBuilder vs StringBuffer.
- Error handling: try-catch-finally blocks.

[Presentation: Java Basics](https://drive.google.com/file/d/1x-oEkT--PojEBBwFoxIOC5eeb5hZjqUA/view?usp=sharing "Java Basics")

# HW 22.02.2019 

No homework 
## useful tools 
 - https://github.com/Genymobile/scrcpy

 - https://developer.android.com/studio/intro/studio-config

# HW 18.02.2019

## Usefull links . 

progit: https://git-scm.com/book/ru/v2  
https://developer.android.com/guide/  

Create project (github || bitbucket), soursetree  
Push Android Project to master in repo (.gitignore)  
Create 'feature_1', 'feature_n' branches  
Push all branches to remote  
Emulate conflict in a few files  
Merge conflict to master  
Chery Pick  
Rebase  
Stash  

create submodule and create few commits  
make commit in main project which will point to some sha in submodule  

branching model https://habr.com/ru/post/106912/  

Send project theme and url to dsslutskiy@gmail.com
Format
First_Name Last_Name url name_of_proj  
