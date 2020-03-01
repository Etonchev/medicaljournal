import {Patient} from "../patient/patient";
import {Doctor} from "../doctor/doctor";

export class Examination {
  date: Date;
  diagnosis: string;
  prescription: string[];
  sickLeave: SickLeave;
  patient: Patient;
  doctor: Doctor;
}
