import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { LibServiceService } from './lib-service.service';

describe('LibServiceService', () => {
  let service: LibServiceService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
