package tests.real;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.local.TestBase;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

public class AndroidSelenideTests extends TestBase {

    @Test
    @Tag("local")
    void searchTest() {
        step("Skip onboarding page", () -> back());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("wikipedia");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("real")
    void onBoardingPagesTest() {
        step("First onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Second onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Third onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Fourth onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        });
    }

    @Test
    @Tag("real")
    void customizeFeedMenuTest() {
        step("Skip onboarding page", () -> back());

        step("Go to feed customize menu", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/nav_more_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
            $$(AppiumBy.id("android:id/title")).findBy(text("Explore Feed")).click();
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/feed_content_type_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("real")
    void customizeFeedHideAllOptionsTest() {
        step("Skip onboarding pages", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/view_announcement_action_negative")).click();
        });
        step("GO to feed customize menu", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/nav_more_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
            $$(AppiumBy.id("android:id/title")).findBy(text("Explore Feed")).click();
        });
        step("Hide all options", () -> {
            $(AppiumBy.accessibilityId("More options")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/title")).findBy(text("Hide all")).click();
            $(AppiumBy.className("android.widget.ImageButton")).click();
            $(AppiumBy.className("android.widget.ImageButton")).click();
        });
        step("Check empty feed", () ->
                $$(AppiumBy.className("android.widget.TextView")).filterBy(text("There's nothing on your Explore feed"))
                        .shouldHave(size(1)));
    }

    @Test
    @Tag("real")
    void changeLanguageTest() {
        step("Skip onboarding pages", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/view_announcement_action_negative")).click();
        });
        step("GO to language menu", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/nav_more_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
            $$(AppiumBy.id("android:id/title")).findBy(text("Wikipedia languages")).click();
        });
        step("Select russian language", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title"))
                    .findBy(text("ADD LANGUAGE")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/localized_language_name"))
                    .findBy(text("Русский")).click();
        });
        step("Check language added", () ->
                $$(AppiumBy.className("android.widget.TextView")).filterBy(text("Русский"))
                        .shouldHave(size(1)));
    }

    @Test
    @Tag("real")
    void mainMenuTest() {
        step("Skip onboarding pages", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/view_announcement_action_negative")).click();
        });
        step("Check 'Saved' page", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/navigation_bar_item_small_label_view"))
                    .findBy(text("Saved")).click();
            $$(AppiumBy.className("android.widget.TextView")).filterBy(text("Saved"))
                    .shouldHave(sizeGreaterThan(0));
        });
        step("Check 'Search' page", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/navigation_bar_item_small_label_view"))
                    .findBy(text("Search")).click();
            $$(AppiumBy.className("android.widget.TextView")).filterBy(text("Search"))
                    .shouldHave(sizeGreaterThan(0));
        });
        step("Check 'Edits' page", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/navigation_bar_item_small_label_view"))
                    .findBy(text("Edits")).click();
            $$(AppiumBy.className("android.widget.TextView")).filterBy(text("Edits"))
                    .shouldHave(sizeGreaterThan(0));
        });
        step("Check 'More' page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/nav_more_container")).click();
            $$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(texts("LOG IN / JOIN WIKIPEDIA", "Settings", "Donate"));
            back();
        });
        step("Check 'Explore' page", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/navigation_bar_item_small_label_view"))
                    .findBy(text("Explore")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/view_card_header_title"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }
}

