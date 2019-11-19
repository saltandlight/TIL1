from django.db import models

# Create your models here.
class Article(models.Model):
    title = models.CharField(max_length=16)
    contents = models.TextField()
    creator = models.CharField(max_length=8)
    created_at = models.DateTimeField(auto_now_add=True, null=True)
    updated_at = models.DateTimeField(auto_now=True, null=True)

    # 문자열화 해주는 함수임
    def __str__(self):
        return f'{self.title} - created by {{self.creator}}'

    def created_by(self):
        return "created by"+self.creator

    def datetime_to_string(self):
        return self.created_at.strftime("%Y-%m-%d")