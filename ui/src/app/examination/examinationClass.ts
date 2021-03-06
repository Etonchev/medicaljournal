import {Patient} from "../patient/patient";
import {Doctor} from "../doctor/doctor";
import {SickLeave} from "./sickLeave";

export class Examination {
  id: string;
  date: Date;
  diagnosis: string;
  prescriptionText: string;
  prescription: string[];
  sickLeave: SickLeave;
  patient: Patient;
  patientEGN: string;
  doctor: Doctor;
  doctorUIN: string;
  patientFirstName: string;
  patientLastName: string;
}
