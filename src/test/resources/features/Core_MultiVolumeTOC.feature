@Core_Regression
Feature: Multi-volume table of contents

	Background:
		When I launch ProView
		And I sign in
		Then I verify that I am in the library
		When I open the title "AutomatedMultiVolumeTOCV1"
		And I open the options menu
		And I click on "Layout" option of Options Menu

	Scenario: Core_MultiVolumeTOC_Pages
		And I select the "Pages" layout option
		And I close the options menu
		When I open the table of contents
		And I click the section "Introduction"
		Then I verify that the content reads "This is the 41st edition"
		And I close the TOC
		When I open the options menu
		And I sign out
		Then I close the app

	Scenario: Core_MultiVolumeTOC_Scroll
		And I select the "Scroll" layout option
		And I close the options menu
		When I open the table of contents
		And I click the section "Introduction"
		Then I verify that the content reads "This is the 41st edition"
		And I close the TOC
		When I open the options menu
		And I sign out
		Then I close the app
		