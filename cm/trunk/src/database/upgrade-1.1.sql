CREATE TABLE profile_field_value
(
    profile_field_value_id  INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    profile_field_id        INTEGER NOT NULL,
    test_message_id         INTEGER NOT NULL,
    field_value             VARCHAR(2500) NOT NULL
);

ALTER TABLE profile_field ADD COLUMN profile_name VARCHAR(250) NOT NULL DEFAULT 'Z22';


CREATE TABLE agreement
(
    agreement_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    agreement_title   VARCHAR(250) NOT NULL,
    agreement_text    TEXT NOT NULL
);

REPLACE INTO agreement (agreement_id, agreement_title, agreement_text) VALUES
(1, 'AART User Agreement', '<h2>Terms and Conditions ("Terms")</h2>
<p>Last updated: March 2, 2016</p>

<p>Please read these Terms and Conditions ("Terms") carefully before using this application. 
Your access to and use of this application is conditioned on your acceptance of and compliance with
these Terms. These Terms apply to all users and others who access or use the application.
By accessing or using the application you agree to be bound by these Terms. If you disagree
with any part of the terms, please contact AIRA before accessing the application. Any use of this application
will constitute acceptance of these Terms.</p>

<h3>Purpose</h3>
<p>The sole purpose of this application is to allow IIS program staff, IIS technical staff and IIS vendors to assess their system''s 
alignment with standards and best practices. The information contained in this application is to be used for quality
improvement purposes only, and is not to be released beyond IIS program, technical and vendor use. AIRA shall not be responsible or
liable for the accuracy, usefulness, or availability of any information made available via this application, and shall not be responsible or liable 
for any error or omissions in that information. </p>

<h3>Content</h3>
<p>Our application allows users to access test results for their pre-production test site. Every effort has been made to eliminate the 
unlikely risk of exposing protected health information, including using only artificial test data in submissions and queries, and redacting all data elements
that could inadvertently contain any identifiable demographic data. That being said, we ask that you 
treat these data securely, as you would any data you access in the course of typical public health business. If data are downloaded or printed, 
you are responsible for maintaining the security of these data in your environment. </p>

<h3>Links To Other Web Sites</h3>
<p>Our application may contain links to third-party web sites or services that are not owned or controlled
by AIRA. AIRA has no control over, and assumes no responsibility for, the content,
privacy policies, or practices of any third party web sites or services. AIRA cannot be responsible or liable, directly or indirectly, for any
damage or loss caused or alleged to be caused by or in connection with use of or reliance on any
such content, goods or services available on or through any such web sites or services.</p>

<h3>Changes</h3>
<p>We reserve the right to modify or replace these Terms at any time. We will notify and gain agreement from 
users regarding any new terms as they take effect. </p>

<h3>Contact Us</h3>
<p>If you have any questions about these Terms, please contact AIRA at 202-552-0208.</p>

<p>By clicking agree, I acknowledge that:</p>
<ul>
<li>I have read and accepted the Terms above</li>
<li>I am using this application for the sole purpose of assessing my system''s alignment with standards</li>
</ul>
</p>');


ALTER TABLE application_user ADD COLUMN (agreement_id INTEGER);
ALTER TABLE application_user ADD COLUMN (agreement_date DATE);
ALTER TABLE application_user ADD COLUMN (agreement_signature VARCHAR(250));
ALTER TABLE application ADD COLUMN (agreement_id INTEGER);

