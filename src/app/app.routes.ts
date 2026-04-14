import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { RegistrationComponent } from './registration/registration';
import { VehicleComponent } from './vehicle/vehicle';
import { ServiceRequestComponent } from './service-request/service-request';
import { ServicesComponent } from './services/services';
import { AdminComponent } from './admin/admin';
import { MechanicComponent } from './mechanic/mechanic';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'vehicle', component: VehicleComponent },
  { path: 'service-request', component: ServiceRequestComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'mechanic', component: MechanicComponent },
];