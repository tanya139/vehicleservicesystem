import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './services.html'
})
export class ServicesComponent implements OnInit {

  serviceHistory: any[] = [];

  constructor(private http: HttpService) {}

  ngOnInit(): void {
    this.loadHistory();
  }

  loadHistory() {
    this.http.get('/api/owner/services').subscribe({
      next: (data: any) => this.serviceHistory = data,
      error: () => console.error('Failed to load service history')
    });
  }
}