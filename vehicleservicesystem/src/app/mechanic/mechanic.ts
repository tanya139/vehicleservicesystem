import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-mechanic',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './mechanic.html'
})
export class MechanicComponent implements OnInit {

  services: any[] = [];
  message = '';

  constructor(private http: HttpService) {}

  ngOnInit() {
    this.loadServices();
  }

  loadServices() {
    this.http.get('/api/mechanic/services').subscribe({
      next: (data: any) => this.services = data,
      error: () => console.error('Failed to load assigned services')
    });
  }

  updateStatus(serviceId: number, status: string) {
    this.http.put(`/api/mechanic/update/${serviceId}?status=${status}`, {})
    .subscribe({
      next: () => {
        this.message = `Status updated to ${status}`;
        this.loadServices();
      },
      error: () => this.message = 'Update failed'
    });
  }
}