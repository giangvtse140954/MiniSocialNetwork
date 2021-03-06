USE [MiniSocialNetwork]
GO
/****** Object:  Table [dbo].[tblArticle]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblArticle](
	[postId] [int] IDENTITY(1,1) NOT NULL,
	[image] [varchar](1000) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[email] [varchar](50) NULL,
	[statusId] [int] NULL,
	[date] [date] NOT NULL,
 CONSTRAINT [PK__tblArtic__DD0C739A90E56A0B] PRIMARY KEY CLUSTERED 
(
	[postId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblComment]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblComment](
	[cmtId] [int] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](500) NOT NULL,
	[date] [date] NOT NULL,
	[statusId] [int] NOT NULL,
	[email] [varchar](50) NULL,
	[postId] [int] NULL,
	[notifyId] [int] NULL,
 CONSTRAINT [PK__tblComme__756640BB02AB9554] PRIMARY KEY CLUSTERED 
(
	[cmtId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblEmotion]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblEmotion](
	[emoId] [int] IDENTITY(1,1) NOT NULL,
	[isLiked] [bit] NOT NULL,
	[date] [date] NOT NULL,
	[postId] [int] NULL,
	[email] [varchar](50) NULL,
	[notifyId] [int] NULL,
 CONSTRAINT [PK__tblEmoti__AFF1F2342891529B] PRIMARY KEY CLUSTERED 
(
	[emoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblNotify]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblNotify](
	[notifyId] [int] IDENTITY(1,1) NOT NULL,
	[postId] [int] NULL,
	[email] [varchar](50) NULL,
	[date] [date] NOT NULL,
	[type] [int] NOT NULL,
 CONSTRAINT [PK__tblNotif__BA7EF5C642EAF7BD] PRIMARY KEY CLUSTERED 
(
	[notifyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [varchar](20) NOT NULL,
 CONSTRAINT [PK__tblStatu__36257A185CC9D9FE] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [varchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[statusId] [int] NOT NULL,
	[code] [varchar](6) NULL,
 CONSTRAINT [PK__tblUser__AB6E6165C4F4974B] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[tblArticle] ON 

INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (2, N'nha.jpg', N'Qua dao xinh xan', N'this peach is so nice.', N'giangvtse140954@fpt.edu.vn', 3, CAST(N'2019-11-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (3, N'nha.jpg', N'Cai xanh xanh', N'this is not brocolli.', N'vuthugiang2610@gmail.com', 3, CAST(N'2019-10-26' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (4, N'nha.jpg', N'Phơi khăn cho vui', N'nhin buc nay qua la dep luon.', N'dothihoa2512@gmail.com', 3, CAST(N'2019-10-26' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (5, N'nha.jpg', N'Nhiều nhà', N'Cái này chụp ở đâu đó bên Thụy Sĩ, người ta chụp chứ hổng phải tui, tui chỉ lấy trên mạng về rồi đăng lên thoai.', N'dothihoa2512@gmail.com', 3, CAST(N'2018-06-24' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (6, N'nha.jpg', N'Dưa hấu đỏ', N'Ừ thì đương nhiên dưa hấu thì màu đỏ rồi đó!', N'vuthugiang2610@gmail.com', 3, CAST(N'2020-06-25' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (7, N'nha.jpg', N'Trứng xinh', N'khong khong', N'vuthugiang2610@gmail.com', 3, CAST(N'2017-02-22' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (26, N'nha.jpg', N'Khan kha banh do', N'Toi muon tat nang di, cho mau dung nhat mat, toi muon buoc gio lai, cho huong dung bay di', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (27, N'nha.jpg', N'These houses are quite white', N'They are really white, so they are too bright.', N'vuthugiang2610@gmail.com', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (28, N'nha.jpg', N'dang them thu', N'cai nay lam on duoc di, cau troi khan phat', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (29, N'nha.jpg', N'Hi thereeee', N'helloooo', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (30, N'nha.jpg', N'hey', N'heyheyhey', N'dothihoa2512@gmail.com', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (31, N'nha.jpg', N'watermelon', N'this watermelon looks so scrumptious', N'dothihoa2512@gmail.com', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (34, N'nha.jpg', N'hehe', N'hhhhhhhhhhhhh', N'dothihoa2512@gmail.com', 3, CAST(N'2017-02-02' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (35, N'nha.jpg', N'kek', N'kkkkkkaaaaaaa', N'giangvtse140954@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (36, N'nha.jpg', N'kekekek', N'hahahaha', N'giangvtse140954@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (37, N'nha.jpg', N'kekekek', N'hahahaha', N'giangvtse140954@fpt.edu.vn', 4, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (38, N'nha.jpg', N'bao bao dang yeu', N'hahahahah thoi ngoi', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (39, N'nha.jpg', N'toi qua roi chuan bi ngu thoi', N'hahah ngu rat la vui', N'giangvtse140954@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (40, N'nha.jpg', N'them nua cho du 10 row', N'yeah yeahhhh', N'giangvtse140954@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (41, N'nha.jpg', N'keke', N'asjkjdsvhbshjvbhjfsv', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (42, N'nha.jpg', N'them nua cho du 20 row chu', N'nham ty', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
INSERT [dbo].[tblArticle] ([postId], [image], [title], [description], [email], [statusId], [date]) VALUES (43, N'nha.jpg', N'tuong dau paging sai', N'hen ma no khong sai', N'hungnvse140996@fpt.edu.vn', 3, CAST(N'2020-09-30' AS Date))
SET IDENTITY_INSERT [dbo].[tblArticle] OFF
SET IDENTITY_INSERT [dbo].[tblComment] ON 

INSERT [dbo].[tblComment] ([cmtId], [content], [date], [statusId], [email], [postId], [notifyId]) VALUES (116, N'qua la dep', CAST(N'2020-09-30' AS Date), 3, N'giangvtse140954@fpt.edu.vn', 39, 95)
SET IDENTITY_INSERT [dbo].[tblComment] OFF
SET IDENTITY_INSERT [dbo].[tblEmotion] ON 

INSERT [dbo].[tblEmotion] ([emoId], [isLiked], [date], [postId], [email], [notifyId]) VALUES (77, 1, CAST(N'2020-09-30' AS Date), 35, N'giangvtse140954@fpt.edu.vn', 90)
INSERT [dbo].[tblEmotion] ([emoId], [isLiked], [date], [postId], [email], [notifyId]) VALUES (78, 0, CAST(N'2020-09-30' AS Date), 26, N'giangvtse140954@fpt.edu.vn', 92)
INSERT [dbo].[tblEmotion] ([emoId], [isLiked], [date], [postId], [email], [notifyId]) VALUES (79, 1, CAST(N'2020-09-30' AS Date), 38, N'hungnvse140996@fpt.edu.vn', 93)
INSERT [dbo].[tblEmotion] ([emoId], [isLiked], [date], [postId], [email], [notifyId]) VALUES (80, 1, CAST(N'2020-09-30' AS Date), 39, N'giangvtse140954@fpt.edu.vn', 94)
INSERT [dbo].[tblEmotion] ([emoId], [isLiked], [date], [postId], [email], [notifyId]) VALUES (81, 1, CAST(N'2020-09-30' AS Date), 26, N'hungnvse140996@fpt.edu.vn', 96)
SET IDENTITY_INSERT [dbo].[tblEmotion] OFF
SET IDENTITY_INSERT [dbo].[tblNotify] ON 

INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (90, 35, N'giangvtse140954@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 1)
INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (92, 26, N'giangvtse140954@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 0)
INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (93, 38, N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 1)
INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (94, 39, N'giangvtse140954@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 1)
INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (95, 39, N'giangvtse140954@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 2)
INSERT [dbo].[tblNotify] ([notifyId], [postId], [email], [date], [type]) VALUES (96, 26, N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-30' AS Date), 1)
SET IDENTITY_INSERT [dbo].[tblNotify] OFF
SET IDENTITY_INSERT [dbo].[tblStatus] ON 

INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (1, N'New')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (2, N'Activated')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (3, N'Active')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (4, N'Deleted')
SET IDENTITY_INSERT [dbo].[tblStatus] OFF
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'dothihoa2512@gmail.com', N'do thi hoa', N'd53a998b7bdca1a3eb93a32d1ec94af6090c1a349326641b25e170caf08ccb87', 2, N'123123')
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'giangvtse140954@fpt.edu.vn', N'Quả Thu Giang', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 1, N'123456')
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'hungnvse140996@fpt.edu.vn', N'Hung Nguyen', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 2, N'817527')
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'nghungg0811@gmail.com', N'Van Hung', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, N'507680')
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'trannhan380381@gmail.com', N'Tran Nhan', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, N'063624')
INSERT [dbo].[tblUser] ([email], [name], [password], [statusId], [code]) VALUES (N'vuthugiang2610@gmail.com', N'đỗ thị hoa', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 1, N'012345')
ALTER TABLE [dbo].[tblArticle]  WITH CHECK ADD  CONSTRAINT [FK__tblArticl__email__5CD6CB2B] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblArticle] CHECK CONSTRAINT [FK__tblArticl__email__5CD6CB2B]
GO
ALTER TABLE [dbo].[tblArticle]  WITH CHECK ADD  CONSTRAINT [FK__tblArticl__statu__5DCAEF64] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblArticle] CHECK CONSTRAINT [FK__tblArticl__statu__5DCAEF64]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK__tblCommen__email__60A75C0F] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK__tblCommen__email__60A75C0F]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK__tblCommen__postI__619B8048] FOREIGN KEY([postId])
REFERENCES [dbo].[tblArticle] ([postId])
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK__tblCommen__postI__619B8048]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK_NotiComment] FOREIGN KEY([notifyId])
REFERENCES [dbo].[tblNotify] ([notifyId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK_NotiComment]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [StatusForeignKey] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [StatusForeignKey]
GO
ALTER TABLE [dbo].[tblEmotion]  WITH CHECK ADD  CONSTRAINT [FK__tblEmotio__email__656C112C] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblEmotion] CHECK CONSTRAINT [FK__tblEmotio__email__656C112C]
GO
ALTER TABLE [dbo].[tblEmotion]  WITH CHECK ADD  CONSTRAINT [FK__tblEmotio__postI__6477ECF3] FOREIGN KEY([postId])
REFERENCES [dbo].[tblArticle] ([postId])
GO
ALTER TABLE [dbo].[tblEmotion] CHECK CONSTRAINT [FK__tblEmotio__postI__6477ECF3]
GO
ALTER TABLE [dbo].[tblNotify]  WITH CHECK ADD  CONSTRAINT [FK__tblNotify__email__693CA210] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblNotify] CHECK CONSTRAINT [FK__tblNotify__email__693CA210]
GO
ALTER TABLE [dbo].[tblNotify]  WITH CHECK ADD  CONSTRAINT [FK__tblNotify__postI__68487DD7] FOREIGN KEY([postId])
REFERENCES [dbo].[tblArticle] ([postId])
GO
ALTER TABLE [dbo].[tblNotify] CHECK CONSTRAINT [FK__tblNotify__postI__68487DD7]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK__tblUser__statusI__4D94879B] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK__tblUser__statusI__4D94879B]
GO
/****** Object:  StoredProcedure [dbo].[GetArticle]    Script Date: 9/30/2020 10:03:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetArticle] 
 @PageNumber int,
 @RowsOfPage int,
 @SearchValue nvarchar(50)
AS
	SELECT postId, image, title, description, email, statusId, date 
	FROM tblArticle
	where title like '%' + @SearchValue + '%' and statusId = 3
	ORDER BY date desc 
	OFFSET (@PageNumber-1)*@RowsOfPage ROWS
	FETCH NEXT @RowsOfPage ROWS ONLY;
GO
