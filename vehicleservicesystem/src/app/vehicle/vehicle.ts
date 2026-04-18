import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-vehicle',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './vehicle.html'
})
export class VehicleComponent {

  vehicle = { model: '', number: '' };
  message = '';

  constructor(private http: HttpService) {}

  addVehicle() {
    this.http.post('/api/owner/vehicle', this.vehicle).subscribe({
      next: () => {
        this.message = 'Vehicle added successfully!';
        this.vehicle = { model: '', number: '' };
      },
      error: () => this.message = 'Failed to add vehicle'
    });
  }
}