# FileFixer
This is the original code base for the application FileFixer

# Description
```text
The myElearning content management platform provides students with a medium to submit files such as PDFs or word documents. Submissions can be individually or batch  downloaded by lecturers for grading and annotations, and then re-uploaded to provide feedback to the relevant students. 
Files submitted on the myElearning platform must conform to a specific naming convention in order to be accepted by the platform. A common problem which arises when students submit files with names which do not conform to the myElearning naming convention, is that the platform rejects submissions by the lecturer when they’re attempting to reupload the graded file. 

The lecturer is then faced with the tedious task of individually renaming the files to meet the platform’s naming convention.
To mitigate this problem, the FileFixer java application will be able to locate and process zero or more PDF files of  a particular directory that are to be renamed. This will be done by accepting a CSV file containing students marks and feedback information as well as a collection of marked up assignment files named according to a specific convention (convention 1). The application will extract the relevant information from the CSV file and use this information to rename the collection of assignment files according to a specific naming convention (convention 2). 

The FileFixer application will also produce a list of missing submission files based on the CSV file’s student list. In addition, a test suite will be developed for evaluation of the application’s performance. The application code will be documented on a GitHub repository.
The FileFixer application will require the user, in this case the lecturer, to place the CSV file and marked up assignment files in a specific folder called “filesToRename” and then execute the main class. 
After execution, the renamed files will be placed in a nested folder named “renamedFiles”, within the “filesToRename” folder. The successful outcome of the FileFixer application will be measured by the collection of renamed assignment files conforming to a specific convention, (convention 2), that is acceptable by myElearning for upload. The application is intended to be completed by November 17, 2021. 
Additionally, the application will be developed using two design patterns that will later be specified which will conform to SOLID design principles and will be evaluated using a Test-Driven Design.
The team members Nicholas Smith, Jeremy De Freitas, Lorenzo La Caille and Nickal Winter will use their understanding of the SOLID design principles and Design patterns to produce a flexible, resilient to change and easy to maintain project.
```


# Installation
_Instructions for the installation goes here. Tell other users how to install your project locally_

# Usage
_This section you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action._

# Members
_I think for the emails we should use gmail or hotmail here or any other besides our school email_
- Jeremy De Freitas jeremydefreitas@hotmail.com
- Lorenzo La Caille lorenzo.lacaille@hotmail.com
- Nickal Winter nickwinter01@gmail.com
- Nicholas Smith nicosmith.smith3@gmail.com

# Activities
**We can update these as we go or create a log file of the activities done and to be completed**

### Example of how to update the checkbox in markdown
```
use - [ ] for unchecked checkboxes the dash then a space then '[' then a space then ']' then a space and the item you want as a checkbox item 
use - [x] for checked checkboxes
```
- [ ] Create a scope
- [ ] Create a team name??
- [x] Brainstorm what java project we are going to use to make it. Example Maven, Springboot
- [ ] Decide on a file naming convention for the source code files.
