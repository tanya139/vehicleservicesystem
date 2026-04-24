import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpService } from '../services/http';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-vehicle',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './vehicle.html'
})
export class VehicleComponent {

  vehicle = { model: '', number: '' };
  message = '';
  addedVehicle: any = null;

  constructor(private http: HttpService, private auth: AuthService) {}

  addVehicle() {
    this.http.post('/api/owner/vehicle', this.vehicle).subscribe({
      next: (response: any) => {
        this.message = 'Vehicle added successfully!';
        this.addedVehicle = response;
        this.vehicle = { model: '', number: '' };
      },
      error: (err) => {
        if (err.status === 500) {
          this.message = 'Vehicle number already exists!';
        } else if (err.status === 403) {
          this.message = 'Access denied. Please login again.';
        } else {
          this.message = 'Failed to add vehicle. Try again.';
        }
        this.addedVehicle = null;
      }
    });
  }
}