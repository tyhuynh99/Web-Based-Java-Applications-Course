USE [master]
GO
/****** Object:  Database [PetServiceSystem]    Script Date: 07/14/2019 21:25:54 ******/
CREATE DATABASE [PetServiceSystem] ON  PRIMARY 
( NAME = N'PetServiceSystem', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\PetServiceSystem.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PetServiceSystem_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\PetServiceSystem_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PetServiceSystem] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PetServiceSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PetServiceSystem] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [PetServiceSystem] SET ANSI_NULLS OFF
GO
ALTER DATABASE [PetServiceSystem] SET ANSI_PADDING OFF
GO
ALTER DATABASE [PetServiceSystem] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [PetServiceSystem] SET ARITHABORT OFF
GO
ALTER DATABASE [PetServiceSystem] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [PetServiceSystem] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [PetServiceSystem] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [PetServiceSystem] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [PetServiceSystem] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [PetServiceSystem] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [PetServiceSystem] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [PetServiceSystem] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [PetServiceSystem] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [PetServiceSystem] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [PetServiceSystem] SET  DISABLE_BROKER
GO
ALTER DATABASE [PetServiceSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [PetServiceSystem] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [PetServiceSystem] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [PetServiceSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [PetServiceSystem] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [PetServiceSystem] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [PetServiceSystem] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [PetServiceSystem] SET  READ_WRITE
GO
ALTER DATABASE [PetServiceSystem] SET RECOVERY SIMPLE
GO
ALTER DATABASE [PetServiceSystem] SET  MULTI_USER
GO
ALTER DATABASE [PetServiceSystem] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [PetServiceSystem] SET DB_CHAINING OFF
GO
USE [PetServiceSystem]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Birthdate] [date] NOT NULL,
	[Role] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'Admin1', N'1', N'Admin 1', N'admin1@gmail.com', CAST(0xCA240B00 AS Date), N'admin')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'Admin2', N'2', N'Admin 2', N'admin2@gmail.com', CAST(0xC9240B00 AS Date), N'admin')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'admin3', N'3', N'Admin 3', N'admin3@gmail.com', CAST(0xDF940A00 AS Date), N'admin')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'admin7', N'7', N'admin 7', N'admin7@gmail.com', CAST(0x6C230B00 AS Date), N'admin')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'admin9', N'9', N'Admin 9', N'admin9@gmail.com', CAST(0x6C230B00 AS Date), N'admin')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'User1', N'1', N'User 1', N'user1@gmail.com', CAST(0x8B230B00 AS Date), N'user')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'User2', N'2', N'User 2', N'user2@gmail.com', CAST(0x18230B00 AS Date), N'user')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'User3', N'3', N'User 3', N'user3@gmail.com', CAST(0x18230B00 AS Date), N'user')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'User4', N'4', N'User 4', N'user4@gmail.com', CAST(0x18230B00 AS Date), N'user')
INSERT [dbo].[Account] ([Username], [Password], [Fullname], [Email], [Birthdate], [Role]) VALUES (N'user5', N'5', N'User 5', N'user5@gmail.com', CAST(0x55230B00 AS Date), N'user')
/****** Object:  Table [dbo].[Accessory]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Accessory](
	[AccId] [varchar](50) NOT NULL,
	[AccName] [varchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[Description] [varchar](max) NULL,
 CONSTRAINT [PK_Accessory] PRIMARY KEY CLUSTERED 
(
	[AccId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc1', N'Pet outdoor bowl', 300, 98, 1, N'Portable Wholesale travel folding dog bowls Material: Water proof/inside, Polyester/outside Size: 15cm(diameter)*10.5cm(height)')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc2', N'Dog Pooper Scooper Pooper Bag', 400, 80, 1, N'Suitable for: Small medium large dogs and cats')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc3', N'Soft Mesh Harness Dog', 550, 50, 1, N'Black, Grey, Red, Pink, Blue, Purple, Green, Yellow, Orange, Brown, Multicolor')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc4', N'Silicone Collapsible Travel Pet Dog Bowl', 700, 19, 1, N'Black, Grey, Red, Pink, Blue, Purple, Green, Yellow, Orange, Brown, Multicolor')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc5', N'Standard Waterproof Breathing Cat Hammock', 500, 30, 1, N'Black, Grey, Red, Pink, Blue, Purple, Green, Yellow, Orange, Brown, Multicolor')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc6', N'Pet Beauty Dryer', 400, 30, 1, N'Black, Grey, Red, Pink, Blue, Purple, Green, Yellow, Orange, Brown, Multicolor')
INSERT [dbo].[Accessory] ([AccId], [AccName], [Price], [Quantity], [Status], [Description]) VALUES (N'Acc7', N'Dog Bed', 250, 35, 1, N'Black, Grey, Red, Pink, Blue, Purple, Green, Yellow, Orange, Brown, Multicolor')
/****** Object:  Table [dbo].[PaymentDetails]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PaymentDetails](
	[PaymentId] [varchar](50) NOT NULL,
	[Method] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PaymentDetails] PRIMARY KEY CLUSTERED 
(
	[PaymentId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[PaymentDetails] ([PaymentId], [Method]) VALUES (N'Pay1', N'Visa card')
INSERT [dbo].[PaymentDetails] ([PaymentId], [Method]) VALUES (N'Pay2', N'Credit  card')
INSERT [dbo].[PaymentDetails] ([PaymentId], [Method]) VALUES (N'Pay3', N'COD')
/****** Object:  Table [dbo].[SlotDetails]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SlotDetails](
	[Slot] [int] NOT NULL,
	[Time] [varchar](50) NOT NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_SlotDetails] PRIMARY KEY CLUSTERED 
(
	[Slot] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (1, N'7:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (2, N'9:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (3, N'11:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (4, N'13:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (5, N'15:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (6, N'17:00', 1)
INSERT [dbo].[SlotDetails] ([Slot], [Time], [Status]) VALUES (7, N'19:00', 1)
/****** Object:  Table [dbo].[Service]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Service](
	[ServiceId] [varchar](50) NOT NULL,
	[ServiceName] [varchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Slot] [int] NOT NULL,
	[Status] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Service] PRIMARY KEY CLUSTERED 
(
	[ServiceId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'asdas', N'Grooming', 500, 3, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'Ser1', N'Washing', 400, 1, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'ser123', N'123', 123, 3, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'ser1232', N'123', 123, 5, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'Ser2', N'Training', 200, 5, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'Ser3', N'Day care', 99, 5, N'1         ')
INSERT [dbo].[Service] ([ServiceId], [ServiceName], [Price], [Slot], [Status]) VALUES (N'Ser4', N'Pet Sitting', 150, 5, N'1         ')
/****** Object:  Table [dbo].[Pet]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Pet](
	[PetId] [varchar](50) NOT NULL,
	[PetName] [varchar](50) NOT NULL,
	[Age] [int] NOT NULL,
	[Type] [varchar](50) NOT NULL,
	[Owner] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Pet] PRIMARY KEY CLUSTERED 
(
	[PetId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Pet] ([PetId], [PetName], [Age], [Type], [Owner]) VALUES (N'1', N'Alice', 1, N'Dog', N'user4')
INSERT [dbo].[Pet] ([PetId], [PetName], [Age], [Type], [Owner]) VALUES (N'ad2', N'Bull', 1, N'Cat', N'user1')
INSERT [dbo].[Pet] ([PetId], [PetName], [Age], [Type], [Owner]) VALUES (N'Pet2', N'Tom', 4, N'Dog', N'User2')
INSERT [dbo].[Pet] ([PetId], [PetName], [Age], [Type], [Owner]) VALUES (N'Pet4', N'Lu', 3, N'Cat', N'User3')
/****** Object:  Table [dbo].[Invoice]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Invoice](
	[OrderId] [varchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[PaymentId] [varchar](50) NOT NULL,
	[PhoneNumber] [varchar](10) NOT NULL,
	[Address] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Invoice] ([OrderId], [Date], [Username], [PaymentId], [PhoneNumber], [Address]) VALUES (N'user1-Sun Jul 14 15:57:45 ICT 2019-15:57:45.544', CAST(0xE53F0B00 AS Date), N'user1', N'Pay1', N'1231231231', N'test1')
INSERT [dbo].[Invoice] ([OrderId], [Date], [Username], [PaymentId], [PhoneNumber], [Address]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', CAST(0xE53F0B00 AS Date), N'user1', N'Pay2', N'1231231231', N'Test Invoice')
/****** Object:  Table [dbo].[InvoiceServiceDetails]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[InvoiceServiceDetails](
	[OrderId] [varchar](50) NOT NULL,
	[PetId] [varchar](50) NOT NULL,
	[SerId] [varchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Slot] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Ser2', CAST(0xE63F0B00 AS Date), 4)
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'asdas', CAST(0xE63F0B00 AS Date), 2)
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Ser4', CAST(0xE63F0B00 AS Date), 1)
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Ser3', CAST(0xE63F0B00 AS Date), 1)
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'ser1232', CAST(0xE63F0B00 AS Date), 1)
INSERT [dbo].[InvoiceServiceDetails] ([OrderId], [PetId], [SerId], [Date], [Slot]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'ser123', CAST(0xE63F0B00 AS Date), 1)
/****** Object:  Table [dbo].[InvoiceDetails]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[InvoiceDetails](
	[OrderId] [varchar](50) NOT NULL,
	[PetId] [varchar](50) NOT NULL,
	[AccId] [varchar](50) NOT NULL,
	[Quantity] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[InvoiceDetails] ([OrderId], [PetId], [AccId], [Quantity]) VALUES (N'user1-Sun Jul 14 15:57:45 ICT 2019-15:57:45.544', N'ad2', N'Acc1', 1)
INSERT [dbo].[InvoiceDetails] ([OrderId], [PetId], [AccId], [Quantity]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Acc4', 1)
INSERT [dbo].[InvoiceDetails] ([OrderId], [PetId], [AccId], [Quantity]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Acc2', 20)
INSERT [dbo].[InvoiceDetails] ([OrderId], [PetId], [AccId], [Quantity]) VALUES (N'user1-Sun Jul 14 20:43:08 ICT 2019-20:43:09.043', N'ad2', N'Acc1', 1)
/****** Object:  Table [dbo].[HaveAccessories]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HaveAccessories](
	[AccId] [varchar](50) NOT NULL,
	[PetId] [varchar](50) NOT NULL,
	[Quantity] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[HaveAccessories] ([AccId], [PetId], [Quantity]) VALUES (N'Acc1', N'ad2', 1)
INSERT [dbo].[HaveAccessories] ([AccId], [PetId], [Quantity]) VALUES (N'Acc4', N'ad2', 1)
INSERT [dbo].[HaveAccessories] ([AccId], [PetId], [Quantity]) VALUES (N'Acc2', N'ad2', 20)
INSERT [dbo].[HaveAccessories] ([AccId], [PetId], [Quantity]) VALUES (N'Acc1', N'ad2', 1)
/****** Object:  Table [dbo].[BookService]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookService](
	[SerId] [varchar](50) NOT NULL,
	[PetId] [varchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Slot] [int] NOT NULL,
	[Status] [bit] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'ser123', N'ad2', CAST(0xE63F0B00 AS Date), 1, 0)
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'ser1232', N'ad2', CAST(0xE63F0B00 AS Date), 1, 0)
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'Ser4', N'ad2', CAST(0xE63F0B00 AS Date), 1, 0)
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'Ser3', N'ad2', CAST(0xE63F0B00 AS Date), 1, 0)
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'asdas', N'ad2', CAST(0xE63F0B00 AS Date), 2, 0)
INSERT [dbo].[BookService] ([SerId], [PetId], [Date], [Slot], [Status]) VALUES (N'Ser2', N'ad2', CAST(0xE63F0B00 AS Date), 4, 0)
/****** Object:  Table [dbo].[AccCart]    Script Date: 07/14/2019 21:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AccCart](
	[AccId] [varchar](50) NOT NULL,
	[PetId] [varchar](50) NOT NULL,
	[Quantity] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_Pet_Account]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[Pet]  WITH CHECK ADD  CONSTRAINT [FK_Pet_Account] FOREIGN KEY([Owner])
REFERENCES [dbo].[Account] ([Username])
GO
ALTER TABLE [dbo].[Pet] CHECK CONSTRAINT [FK_Pet_Account]
GO
/****** Object:  ForeignKey [FK_Invoice_Account]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Account] FOREIGN KEY([Username])
REFERENCES [dbo].[Account] ([Username])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Account]
GO
/****** Object:  ForeignKey [FK_Invoice_PaymentDetails]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_PaymentDetails] FOREIGN KEY([PaymentId])
REFERENCES [dbo].[PaymentDetails] ([PaymentId])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_PaymentDetails]
GO
/****** Object:  ForeignKey [FK_InvoiceServiceDetails_Invoice]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceServiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceServiceDetails_Invoice] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Invoice] ([OrderId])
GO
ALTER TABLE [dbo].[InvoiceServiceDetails] CHECK CONSTRAINT [FK_InvoiceServiceDetails_Invoice]
GO
/****** Object:  ForeignKey [FK_InvoiceServiceDetails_Pet]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceServiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceServiceDetails_Pet] FOREIGN KEY([PetId])
REFERENCES [dbo].[Pet] ([PetId])
GO
ALTER TABLE [dbo].[InvoiceServiceDetails] CHECK CONSTRAINT [FK_InvoiceServiceDetails_Pet]
GO
/****** Object:  ForeignKey [FK_InvoiceServiceDetails_Service]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceServiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceServiceDetails_Service] FOREIGN KEY([SerId])
REFERENCES [dbo].[Service] ([ServiceId])
GO
ALTER TABLE [dbo].[InvoiceServiceDetails] CHECK CONSTRAINT [FK_InvoiceServiceDetails_Service]
GO
/****** Object:  ForeignKey [FK_InvoiceServiceDetails_SlotDetails]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceServiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceServiceDetails_SlotDetails] FOREIGN KEY([Slot])
REFERENCES [dbo].[SlotDetails] ([Slot])
GO
ALTER TABLE [dbo].[InvoiceServiceDetails] CHECK CONSTRAINT [FK_InvoiceServiceDetails_SlotDetails]
GO
/****** Object:  ForeignKey [FK_InvoiceDetails_Accessory]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceDetails_Accessory] FOREIGN KEY([AccId])
REFERENCES [dbo].[Accessory] ([AccId])
GO
ALTER TABLE [dbo].[InvoiceDetails] CHECK CONSTRAINT [FK_InvoiceDetails_Accessory]
GO
/****** Object:  ForeignKey [FK_InvoiceDetails_Invoice]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceDetails_Invoice] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Invoice] ([OrderId])
GO
ALTER TABLE [dbo].[InvoiceDetails] CHECK CONSTRAINT [FK_InvoiceDetails_Invoice]
GO
/****** Object:  ForeignKey [FK_InvoiceDetails_Pet]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[InvoiceDetails]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceDetails_Pet] FOREIGN KEY([PetId])
REFERENCES [dbo].[Pet] ([PetId])
GO
ALTER TABLE [dbo].[InvoiceDetails] CHECK CONSTRAINT [FK_InvoiceDetails_Pet]
GO
/****** Object:  ForeignKey [FK_HaveAccessories_Accessory]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[HaveAccessories]  WITH CHECK ADD  CONSTRAINT [FK_HaveAccessories_Accessory] FOREIGN KEY([AccId])
REFERENCES [dbo].[Accessory] ([AccId])
GO
ALTER TABLE [dbo].[HaveAccessories] CHECK CONSTRAINT [FK_HaveAccessories_Accessory]
GO
/****** Object:  ForeignKey [FK_HaveAccessories_Pet]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[HaveAccessories]  WITH CHECK ADD  CONSTRAINT [FK_HaveAccessories_Pet] FOREIGN KEY([PetId])
REFERENCES [dbo].[Pet] ([PetId])
GO
ALTER TABLE [dbo].[HaveAccessories] CHECK CONSTRAINT [FK_HaveAccessories_Pet]
GO
/****** Object:  ForeignKey [FK_BookService_Pet]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[BookService]  WITH CHECK ADD  CONSTRAINT [FK_BookService_Pet] FOREIGN KEY([PetId])
REFERENCES [dbo].[Pet] ([PetId])
GO
ALTER TABLE [dbo].[BookService] CHECK CONSTRAINT [FK_BookService_Pet]
GO
/****** Object:  ForeignKey [FK_BookService_Service]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[BookService]  WITH CHECK ADD  CONSTRAINT [FK_BookService_Service] FOREIGN KEY([SerId])
REFERENCES [dbo].[Service] ([ServiceId])
GO
ALTER TABLE [dbo].[BookService] CHECK CONSTRAINT [FK_BookService_Service]
GO
/****** Object:  ForeignKey [FK_BookService_SlotDetails]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[BookService]  WITH CHECK ADD  CONSTRAINT [FK_BookService_SlotDetails] FOREIGN KEY([Slot])
REFERENCES [dbo].[SlotDetails] ([Slot])
GO
ALTER TABLE [dbo].[BookService] CHECK CONSTRAINT [FK_BookService_SlotDetails]
GO
/****** Object:  ForeignKey [FK_AccCart_Accessory]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[AccCart]  WITH CHECK ADD  CONSTRAINT [FK_AccCart_Accessory] FOREIGN KEY([AccId])
REFERENCES [dbo].[Accessory] ([AccId])
GO
ALTER TABLE [dbo].[AccCart] CHECK CONSTRAINT [FK_AccCart_Accessory]
GO
/****** Object:  ForeignKey [FK_AccCart_Pet]    Script Date: 07/14/2019 21:25:55 ******/
ALTER TABLE [dbo].[AccCart]  WITH CHECK ADD  CONSTRAINT [FK_AccCart_Pet] FOREIGN KEY([PetId])
REFERENCES [dbo].[Pet] ([PetId])
GO
ALTER TABLE [dbo].[AccCart] CHECK CONSTRAINT [FK_AccCart_Pet]
GO
