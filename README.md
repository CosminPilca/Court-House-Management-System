# Court-House-Management-System

Court House Management System Documentation

Overview
The Court House Management System is designed to streamline the organization, scheduling, and management of court cases, personnel, and hearings. It enables efficient tracking and assignment of cases, the scheduling of courtrooms, and the management of involved parties such as judges, lawyers, clients, and staff. The system aims to improve the overall efficiency of courthouse operations by providing real-time notifications and comprehensive scheduling tools.
Relationships
A Case can have multiple Hearings, but each Hearing pertains to one Case.
A Judge can oversee multiple Cases and Hearings, but each Case or Hearing is handled by a single Judge.
Clients can register multiple Cases, but each case is associated with only one client.
M Relationships:
Lawyers can handle multiple Cases, and each Case can involve multiple Lawyers. This is managed via a LawyerAssignment entity that facilitates this many-to-many connection.
1:1 Relationships:
Each Hearing is assigned to one Courtroom, ensuring that the courtroom is not double-booked.


![diagram1](https://github.com/user-attachments/assets/87e48ff8-d91a-4ea7-8425-059895f703ee)





Conclusion
The Court House Management System is an integrated platform that simplifies the administration of court cases, hearings, and involved parties. By implementing automated notifications, structured scheduling, and comprehensive case management, the system enhances the efficiency and accuracy of courthouse operations. With a scalable design and robust architecture, it can be adapted to different courthouse sizes and requirements.
