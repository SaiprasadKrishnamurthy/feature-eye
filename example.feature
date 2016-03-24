@config @ignore
Feature: User defined cli templates (custom templates)
  As a network administrator
  I want to configure devices using a user defined cli template
  So that I can manage the configs more efficiently

  Background:
    Given I'm logged in to the PI

  @ui @sanity
  Scenario Outline: Should be able to deploy a "ACL" template to a device type.
    Given I have a user defined "ACL" cli config template with acl number "102", access type "permit", source host "4.4.4.4" and destination ip "5.5.5.5" available to use
    And a "<DeviceType>" to deploy the configs to
    When I deploy the config template
    Then I should see config template deployed successfully
    And the device must be set with acl number as "102", access type as "permit", source host as "4.4.4.4" and destination ip as "5.5.5.5"
    Examples:
      | DeviceType         |
      | ISR_3945G2-1.1.1.1 |
      | CSR_1KV-1.1.1.3    |
      | CRS1_16-1.1.1.2    |
      | NEXUS_7018-1.1.1.4 |
      | ASA-1.1.1.5        |
